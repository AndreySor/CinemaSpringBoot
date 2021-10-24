package com.school21.cinemaspringboot.mapper;


import com.school21.cinemaspringboot.model.Film;
import com.school21.cinemaspringboot.model.Session;
import com.school21.cinemaspringboot.model.response.FilmResponse;
import com.school21.cinemaspringboot.model.response.SessionsResponse;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

//ex01
@Component
public class SessionsResponseMapper {

    private final String pathToImages;

    public SessionsResponseMapper( @Value("${images.folder.path}") String pathToImages) {
        this.pathToImages = pathToImages;
    }

    public List<SessionsResponse> mapToResponse(List<Session> rs) {
        List<SessionsResponse> response = new ArrayList<>();

        if (rs.isEmpty()) {
            return response;
        }
        for (Session s : rs) {
            SessionsResponse sessionsResponse = new SessionsResponse();
            sessionsResponse.setId(s.getId());
            sessionsResponse.setDateTime(s.getDate());

            FilmResponse filmResponse = new FilmResponse(s.getFilm().getTitle(), s.getFilm().getPoster());
            byte[] file = new byte[0];
            try {
                file = FileUtils.readFileToByteArray(new File(pathToImages +
                        filmResponse.getName() + "/" + filmResponse.getPosterUrl()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            String encode = Base64.getEncoder().encodeToString(file);
            filmResponse.setPosterUrl(encode);
            sessionsResponse.setFilm(filmResponse);

            response.add(sessionsResponse);
        }
        return response;
    }

    public void encodePoster(Film film) {
        byte[] file = new byte[0];
        try {
            file = FileUtils.readFileToByteArray(new File(pathToImages +
                    film.getTitle() + "/" + film.getPoster()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String encode = Base64.getEncoder().encodeToString(file);
        film.setPoster(encode);
    }
}
