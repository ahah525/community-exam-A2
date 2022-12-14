<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../common/header.jspf"%>

<section>
    <div class="container px-3 mx-auto">
        <h1 class="font-bold text-lg">채팅방 리스트</h1>

        <ul class="mt-5">
            <c:forEach items="${rooms}" var="room">
            <li class="flex gap-2">
                <a class="w-[40px] hover:underline hover:text-[red]" href="/usr/chat/room/${room.id}">${room.id}</a>
                <a class="hover:underline hover:text-[red] mr-auto" href="/usr/chat/room/${room.id}">
                    ${room.title}
                    #${room.body}
                </a>
                <a onclick="if ( !confirm('정말로 삭제하시겠습니까?') ) return false;" class="hover:underline hover:text-[red] mr-2" href="/usr/chat/deleteRoom/${room.id}">삭제</a>
                <a class="hover:underline hover:text-[red]" href="/usr/chat/modifyRoom/${room.id}">수정</a>
            </c:forEach>
        </ul>
    </div>
</section>

<%@ include file="../common/footer.jspf"%>