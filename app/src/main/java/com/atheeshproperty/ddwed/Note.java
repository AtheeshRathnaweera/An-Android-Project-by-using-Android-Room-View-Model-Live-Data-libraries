package com.atheeshproperty.ddwed;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "note_table")
public class Note {

    @PrimaryKey(autoGenerate = true)//To autogenerate the id
    private int id;

    private String title;

    private String description;

    private int priority;//column name for this member variable is "priority"

    //To ignore a field by " @Ignore "
    //To change the column name @ColumnInfo("name")


    public Note(String title, String description, int priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }
}
