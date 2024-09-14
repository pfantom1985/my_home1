package org.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dto.VoteDTO;
import org.example.service.VoteService;
import org.example.service.api.IVoteService;
import org.example.storage.VoteStorageMemory;
import org.example.storage.api.IVoteStorage;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet(urlPatterns = "/browser/vote")
public class BrowserVoteServlet extends HttpServlet {

    private final static IVoteService voteService = VoteService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.write("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <title>Форма для голосования</title>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "</head>\n" +
                "<body>\n" +
                "<form action=\"" + req.getContextPath() + "/browser/vote\" method=\"POST\">\n" +
                "\n" +
                "    <p><b>Выберите по Вашему мнению лучшего музыкального исполнителя из списка (1 вариант):</b><br>\n" +
                "        <input type=\"radio\" name=\"artist\" value=\"a1\">Teo<br>\n" +
                "        <input type=\"radio\" name=\"artist\" value=\"a2\">Алена Ланская<br>\n" +
                "        <input type=\"radio\" name=\"artist\" value=\"a3\">Александр Солодухо<br>\n" +
                "        <input type=\"radio\" name=\"artist\" value=\"a4\">Макс Корж<br></p>\n" +
                "\n" +
                "    <!--     <p>Выберите артиста:</p>\n" +
                "    <select name=\"artist\">\n" +
                "        <options>Teo</options>\n" +
                "        <options>Алена Ланская</options>\n" +
                "        <options>Александр Солодухо</options>\n" +
                "        <options>Макс Корж</options>\n" +
                "    </select>\n" +
                "    </br> -->\n" +
                "\n" +
                "    <p><b>Выберите по Вашему мнению лучшие музыкальные жанры из списка (3-5 варианта):</b></p>\n" +
                "    <input type=\"checkbox\" name=\"genre\" value=\"Кантри\"><span>Кантри</span>\n" +
                "    <input type=\"checkbox\" name=\"genre\" value=\"Джаз\"><span>Джаз</span>\n" +
                "    <input type=\"checkbox\" name=\"genre\" value=\"Шансон\"><span>Шансон</span>\n" +
                "    <input type=\"checkbox\" name=\"genre\" value=\"Блюз\"><span>Блюз</span>\n" +
                "    <input type=\"checkbox\" name=\"genre\" value=\"Рок\"><span>Рок</span>\n" +
                "    <input type=\"checkbox\" name=\"genre\" value=\"Метал\"><span>Метал</span>\n" +
                "    <input type=\"checkbox\" name=\"genre\" value=\"Диско\"><span>Диско</span>\n" +
                "    <input type=\"checkbox\" name=\"genre\" value=\"Фолк\"><span>Фолк</span>\n" +
                "    <input type=\"checkbox\" name=\"genre\" value=\"Электро\"><span>Электро</span>\n" +
                "    <input type=\"checkbox\" name=\"genre\" value=\"Фанк\"><span>Фанк</span>\n" +
                "    </br>\n" +
                "\n" +
                "    <p><b>Краткая информация о Вас (до 100 символов):</b><br>\n" +
                "    <textarea name=\"about\" cols=\"40\" rows=\"3\" maxlength=\"100\"></textarea></p>\n" +
                "\n" +
                "    <!--    <p>О себе:</p>\n" +
                "    <textarea name=«about» cols=\"40\" rows=\"3\" maxlength=\"100\">\n" +
                "</textarea>\n" +
                "    </p> -->\n" +
                "\n" +
                "    <p><input type=\"submit\" value=\"Отправить\">\n" +
                "        <input type=\"reset\" value=\"Очистить\"></p>\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>"
        );
    }

    @Override
    protected void doPost(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {
        String artist = req.getParameter("artist");
        String[] genre = req.getParameterValues("genre");
        String about = req.getParameter("about");

        PrintWriter writer = resp.getWriter();
        try {
            voteService.create(new VoteDTO(artist, genre, about));
        } catch (IllegalArgumentException e) {
            writer.write("<p>error: " + e.getMessage() + "</p>");
        }
    }
}