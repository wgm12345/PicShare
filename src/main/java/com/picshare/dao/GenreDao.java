package com.picshare.dao;

import com.picshare.entity.Genre;

/**
 * Created by wgm on 17/4/26.
 */

public interface GenreDao {
    public void addGenre(Genre genre);
    public Genre getGenreById(int id);
    public Genre getGenreByName(String name);
}
