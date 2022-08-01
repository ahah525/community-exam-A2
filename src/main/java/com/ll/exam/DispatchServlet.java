package com.ll.exam;

import com.ll.exam.article.ArticleController;
import com.ll.exam.chat.ChatController;
import com.ll.exam.member.MemberController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/usr/*")
public class DispatchServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Rq rq = new Rq(req, resp);

        MemberController memberController = new MemberController();
        ArticleController articleController = new ArticleController();
        ChatController chatController = new ChatController();

        switch (rq.getActionPath()) {
            // 채팅
            case "/usr/chat/writeMessage":
                chatController.writeMessage(rq);
                break;
            case "/usr/chat/writeMessageAjax":
                chatController.writeMessageAjax(rq);
                break;
            case "/usr/chat/createRoom":
                chatController.createRoom(rq);
                break;
            case "/usr/chat/modifyRoom":
                chatController.modifyRoom(rq);
                break;
            // 게시물
            case "/usr/article/write":
                articleController.write(rq);
            case "/usr/article/modify":
                articleController.modify(rq);
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Rq rq = new Rq(req, resp);

        MemberController memberController = new MemberController();
        ArticleController articleController = new ArticleController();
        ChatController chatController = new ChatController();

        // 요청 메서드(get, post)에 따라 구분
        switch (rq.getActionPath()) {
            // 채팅
            case "/usr/chat/createRoom":
                chatController.showCreateRoom(rq);
                break;
            case "/usr/chat/modifyRoom":
                chatController.showModifyRoom(rq);
                break;
            case "/usr/chat/roomList":
                chatController.showRoomList(rq);
                break;
            case "/usr/chat/room":
                chatController.showRoom(rq);
                break;
            case "/usr/chat/roomManual":
                chatController.showRoomManual(rq);
                break;
            case "/usr/chat/getMessages":
                chatController.getMessages(rq);
                break;
            // TODO: POST로 옮기기(roomList.jsp 수정)
            case "/usr/chat/deleteRoom":
                chatController.deleteRoom(rq);
                break;
            case "/usr/chat/deleteMessage":
                chatController.deleteMessage(rq);
                break;
            case "/usr/chat/deleteMessageAjax":
                chatController.deleteMessageAjax(rq);
                break;
            // 게시물
            case "/usr/article/list":
                articleController.showList(rq);
            case "/usr/article/listAuto":
                articleController.showListAuto(rq);
                break;
            case "/usr/article/detail":
                articleController.showDetail(rq);
                break;
            case "/usr/article/getArticles":
                articleController.findAll(rq);
                break;
            case "/usr/article/write":
                articleController.showWrite(rq);
                break;
            case "/usr/article/modify":
                articleController.showModifyForm(rq);
                break;
            case "/usr/member/login":
                memberController.showLogin(rq);
                break;
            // TODO: POST로 옮기기
            case "/usr/article/delete":
                articleController.doDelete(rq);
                break;
        }
    }
}
