package br.com.trancosowiliam.eadbox.module

import android.arch.persistence.room.Room
import br.com.trancosowiliam.eadbox.data.dao.EadboxDatabase
import org.koin.dsl.module.applicationContext

private const val DATABASE_NAME = "DATABASE_NAME"

val daoModule = applicationContext {
    bean(DATABASE_NAME) { "database.db" }

    bean {
        Room.databaseBuilder(get(), EadboxDatabase::class.java, get(DATABASE_NAME)).build().categoryDao()
    }

    bean {
        Room.databaseBuilder(get(), EadboxDatabase::class.java, get(DATABASE_NAME)).build().courseDao()
    }
}