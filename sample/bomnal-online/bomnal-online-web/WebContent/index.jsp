<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ko">
<head>
</head>
<body>
    <form id="frm" name="frm" enctype="multipart/form-data" method="post" commandName="ReqFileUploadDto">
        <table class="board_view">
            <colgroup>
                <col width="15%">
                <col width="*"/>
            </colgroup>
            <caption>게시글 작성</caption>
            <tbody>
                <tr>
                    <th scope="row">제목</th>
                    <td><input type="text" id="TITLE" name="TITLE" class="wdp_90"></input></td>
                </tr>
                <tr>
                    <td colspan="2" class="view_text">
                        <textarea rows="20" cols="100" title="내용" id="CONTENTS" name="CONTENTS"></textarea>
                    </td>
                </tr>
            </tbody>
        </table>
        <input type="file" id="uploadFile" name="uploadFile">
        <input type="hidden" id="category" name="category">
        <br/><br/>
          <button type="button" id="write" onclick="insertBoard();">작성하기</buttton>
    </form>
     
    <script type="text/javascript">

        function fn_openBoardList(){
            var comSubmit = new ComSubmit();
            comSubmit.setUrl("<c:url value='/files/upload' />");
            comSubmit.submit();
        }
         
        function insertBoard(){
        	var ff = document.frm;
            ff.action="/online/files/upload";
            ff.method="post";
            ff.submit();
        }
    </script>
</body>
</html>

