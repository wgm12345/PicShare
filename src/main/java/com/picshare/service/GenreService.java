package com.picshare.service;

import com.picshare.entity.Genre;

/**
 * Created by wgm on 17/4/26.
 */
public interface GenreService {
    public void addGenre(Genre genre);
    public Genre getGenreById(int id);
}
