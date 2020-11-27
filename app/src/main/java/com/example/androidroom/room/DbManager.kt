package com.example.androidroom.room

import android.content.Context
import androidx.room.Room
import com.example.androidroom.entity.User
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DbManager private constructor(context: Context) {

    private val disposable : CompositeDisposable = CompositeDisposable()

    private val db = Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME).build()
    fun getUsers(dbCallback: DbCallback) {
        val d = db.userDao()
                .all
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { users -> dbCallback.onUsersLoaded(users) }
        disposable.add(d)
    }

    fun addUser(dbCallback: DbCallback, user : User) {
        val d = Completable.fromAction { db.userDao().insertAll(user) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { dbCallback.onUserAdded() }
        disposable.add(d)
    }

    fun updateUser(dbCallback: DbCallback, oldUserId : Int, newUser: User) {
        val d = db.userDao().findById(oldUserId)
                .flatMapCompletable {
                    oldUser -> Completable.fromAction{
                        db.userDao().updateUser(oldUser.apply {
                            firstName = newUser.firstName
                            lastName = newUser.lastName
                        })
                    }
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { dbCallback.onUserDeleted() }
        disposable.add(d)
    }

    fun deleteUser(dbCallback: DbCallback, id: Int) {
        val d = db.userDao().findById(id)
                .flatMapCompletable { user -> Completable.fromAction{db.userDao().deleteUser(user)} }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { dbCallback.onUserDeleted() }
        disposable.add(d)
    }

    fun disposeAll(){
        disposable.clear()
    }

    companion object {
        const val DB_NAME = "app_db"
        private var instance: DbManager? = null
        fun getInstance(context: Context): DbManager? {
            if (instance == null) {
                instance = DbManager(context)
            }
            return instance
        }
    }
}