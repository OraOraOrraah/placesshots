<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html>
<head>
</head>
<body>
	<br/><br/>
	Rule
	<br/><br/>
	- ปิดรับการทายผลคู่ที่มีการแข่งในวันนั้นๆ  21:00
	<br/><br/>
	- สามารถทายล่วงหน้าได้
	<br/><br/>
	- นับคะแนนดังนี้
	<br/><br/>
    1. ทายผลถูก (แพ้-ชนะ-เสมอ) ได้ 1 คะแนน
    <br/><br/>
	2. ทายสกอร์ถูกด้วย ได้ 4 คะแนน
	<br/><br/>
	3. ทายผลผิด หรือไม่ทายผล ได้ 0 คะแนน
	<br/><br/>
	4. นับคะแนนเฉพาะในเวลา 90 นาที
	<br/><br/>
	ex
	<br/><br/>
	Brazil - Croatia ผลการแข่งคือ 2-0
	<br/><br/>
	ทาย 2-0 = 4 คะแนน
	<br/><br/>
	ทาย 2-1 = 1 คะแนน
	<br/><br/>
	ทาย 1-1 = 0 คะแนน
</body>
</html>