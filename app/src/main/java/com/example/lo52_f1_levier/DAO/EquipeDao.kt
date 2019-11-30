package com.example.lo52_f1_levier.DAO

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.provider.BaseColumns
import com.example.lo52_f1_levier.model.Equipe
import com.example.lo52_f1_levier.model.CourseDbHelper

class EquipeDao(context : Context) {
    val dbHelper = CourseDbHelper(context)

    fun insertEquipe(titre: String): Long? {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(Equipe.EquipeTable.ENAME, titre)
        }
        val newRowId = db?.insert(Equipe.EquipeTable.NAME, null, values)
        return newRowId
    }

    fun getEquipe(ename:String): Cursor? {
        val db = dbHelper.readableDatabase

        val projection = arrayOf(BaseColumns._ID, Equipe.EquipeTable.ENAME)

        val selection = "${Equipe.EquipeTable.ENAME} = ?"
        val selectionArgs = arrayOf(ename)


        val cursor = db.query(
            Equipe.EquipeTable.NAME,   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            selection,              // The columns for the WHERE clause
            selectionArgs,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            null
        )

        return cursor
    }
    fun deleteEquipe(ename: String): Int {
        val db = dbHelper.writableDatabase
        val selection = "${Equipe.EquipeTable.ENAME} LIKE ?"
        val selectionArgs = arrayOf(ename)
        return db.delete(Equipe.EquipeTable.NAME, selection, selectionArgs)
    }
    fun updateEquipe(oldEname: String,ename: String): Int {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(Equipe.EquipeTable.ENAME, ename)
        }
        val selection = "${Equipe.EquipeTable.ENAME} LIKE ?"
        val selectionArgs = arrayOf(oldEname)
        val count = db.update(
            Equipe.EquipeTable.NAME,
            values,
            selection,
            selectionArgs)
        return count
    }
}