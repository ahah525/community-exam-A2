<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../common/header.jspf"%>

<script>
    function ChatMessageSave__submitForm(form) {
        form.body.value = form.body.value.trim();
        // 내용 입력되지 않은 경우 예외처리
        if ( form.body.value.length == 0 ) {
            form.body.focus();
            return false;
        }
        // fetch방식이 아닌 jquery 방식으로 POST 요청
        $.ajax({
           type: "POST",
           url: "/usr/chat/writeMessageAjax/${room.id}",
            data: {
                body: form.body.value
            },
           dataType: "json",
            success: function (res) {
                console.log(res);
            },
        });
        <%--$.post(--%>
        <%--    '/usr/chat/writeMessageAjax/${room.id}', // 주소, action--%>
        <%--    {--%>
        <%--        body: form.body.value // 폼 내용, input name, value--%>
        <%--    },--%>
        <%--    function (data) { // 콜백 메서드, 통신이 완료된 후, 실행--%>
        <%--        // data.resultCode--%>
        <%--        // data.msg--%>
        <%--        console.log(data.data);--%>
        <%--    },--%>
        <%--    'json' // 받은 데이터를 json 으로 해석하겠다.--%>
        <%--);--%>
        // 값 전송 후 원래 값 비우기
        form.body.value = '';
        form.body.focus();
    }
</script>

<script>
    // 마지막으로 가져온 채팅 메시지 id
    let ChatMessages__lastId = 0;
    function ChatMessages__loadMore() {
        fetch(`/usr/chat/getMessages/${room.id}/?fromId=\${ChatMessages__lastId}`)
            .then(data => data.json())
            .then(responseData => {
                const messages = responseData.data;
                for ( const index in messages ) {
                    const message = messages[index];
                    const html = `
                    <li class="flex">
                        <span>메세지 \${message.id} :</span>
                        &nbsp;
                        <span>\${message.body}</span>
                        &nbsp;
                        <a onclick="if ( !confirm('정말로 삭제하시겠습니까?') ) return false;" class="hover:underline hover:text-[red] mr-2" href="/usr/chat/deleteMessage/\${message.id}?_method=DELETE">삭제</a>
                    </li>
                `;
                    $('.chat-messages').append(html);
                }
                // 마지막으로 추가된 채팅방 메시지 id 업데이트
                if ( messages.length > 0 ) {
                    ChatMessages__lastId = messages[messages.length - 1].id;
                }
                // ChatMessages__loadMore(); // 즉시 실행
                setTimeout(ChatMessages__loadMore, 3000); // ChatMessages__loadMore(); 를 3초 뒤에 수행
            });
    }
</script>

<section>
    <div class="container px-3 mx-auto">
        <h1 class="font-bold text-lg">채팅방</h1>

        <div>
            ${room.title}
        </div>

        <div>
            ${room.body}
        </div>

        <form onsubmit="ChatMessageSave__submitForm(this); return false;" method="POST" action="/usr/chat/writeMessage/${room.id}">
            <input autofocus name="body" type="text" placeholder="메세지를 입력해주세요." class="input input-bordered" />
            <button type="submit" value="" class="btn btn-outline btn-primary">
                작성
            </button>
        </form>

        <ul class="chat-messages mt-5">

        </ul>
    </div>
</section>

<script>
    ChatMessages__loadMore();
</script>

<%@ include file="../common/footer.jspf"%>