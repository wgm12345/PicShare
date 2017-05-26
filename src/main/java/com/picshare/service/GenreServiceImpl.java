package com.picshare.service;

import com.picshare.dao.GenreDao;
import com.picshare.entity.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wgm on 17/4/26.
 */
@Service
public class GenreServiceImpl implements GenreService{
    @Autowired
    GenreDao genreDao;
    @Override
    public void addGenre(Genre genre) {
        this.genreDao.addGenre(genre);
    }

    @Override
    public Genre getGenreById(int id) {
        return this.genreDao.getGenreById(id);
    }
}
