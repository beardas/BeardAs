<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
   <meta charset="UTF-8">
   <title>List</title>
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <script src="/resources/lib/js/jquery-3.4.1.min.js"></script>
</head>
<body>

<script>
   $(document).ready(function(e){
      alert("${message}");
      location.replace("${returnURL}");
   });
</script>
</body>

</html>
