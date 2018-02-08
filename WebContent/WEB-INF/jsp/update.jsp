<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Book's</title>
<script language="javascript" src="jquery-1.8.3.js"></script>
<script language="javascript" src="jquery-ui.js"></script>
<script language="javascript" src="../../Javascript/SG_Validation.js"></script>
<script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>

 <script>
    function refreshPage() 
    {
           window.location.reload("true");

    } 
</script>

 <script type="text/javascript">
    function deleteBook(bookId)
    {
        if(confirm('Do you want to delete this Book ?'))
        {
            var url = 'delete/'+bookId;
           window.location.href = url;
        }
    }
    </script>


<script type="text/javascript">
$(document).ready(function(){
	
	
	$.fn.getNoOfCopies=function(id)
	{
		
	var trId=$(this).closest('tr').attr("class");     //  getting last <tr>   $- sign to define/access jquery |"."= class |  #= id
	
	var copy1=$("."+trId).find("#nofCopy").html();    //  "getting value by id  "  current value of copies 
	
	var Id=$(this).attr(id);                          // Primary key
	
	if(copy1<=0)
	{
		$("."+trId).find('.buy').attr('disabled', true);	
		alert("There are no books to buy!");
		return true;
	}
	else{
		$.ajax({
	    type: "POST",
	    url: "./decreaseNoOfCopies",
	    data: 
	    {
	    	"autoId":Id
	    } ,
	    dataType: "json",
	    success: function(response) 
	    {
	    	$("."+trId).find('#nofCopy').html(response);     // setting the value no of copies 
	    }
	});
	}
	};
	
	$.fn.getAddNoOfCopies=function(id)
	{
		var trId=$(this).closest('tr').attr("class");
		var Id=$(this).attr(id);
		
		
			$.ajax(
					{
		    type: "POST",
		    url: "./increaseNoOfCopies",
		    data: 
		    {
		    	"autoId":Id
		    } ,
		    dataType: "json",
		    success: function(response) 
		    {
		    	$("."+trId).find('#nofCopy').html(response);
		    		     
		    }
			});

		};
		
});

</script>

<link href="<c:url value="resources/css/jquery-ui.css"></c:url>" rel="stylesheet" type="text/css" />

<link href="<c:url value="resources/css/chegus-infotech-style-Hie.css"></c:url>" rel="stylesheet" type="text/css" />
</head>


<body>
<c:url value="/search" var="action"></c:url>
<form:form action="${action}" commandName="bookDetails" modelAttribute="bookDetails">
<table border="0" cellpadding="0" cellspacing="0" class="mainTable">
  <tr>
  
    <td class="headerText" >B<span>OOKS</span></td>
    
    <marquee><b><em>Welcome To Signiwis Technology</em></b></marquee>
  </tr>
  <tr>
    <td class="subHeaderText"><h3>Book Search</h3></td>
  </tr>
  <tr>
    <td><table border="0" cellpadding="0" cellspacing="0" class="innerTable" >
        <tr>

          <td class="labelTextSearch">Book Type </td>
          <td class="fieldTextSearch">
          <form:select path="bookType.id">   
          	<option value="">---Select---</option>
          	<c:forEach  items="${bookTypesList}" var="bookTypes">
          	<form:option value="${bookTypes.id}">${bookTypes.bookType}</form:option>
          	</c:forEach>
          </form:select>
          </td> 
          
         
         <td class="labelTextSearch"> Book ID</td>
         <td class="fieldTextSearch"><form:input path="bookId" type="text" class="textStyle_130"/></td>
         <td class="labelTextSearch"> Book Name</td>
         <td class="fieldTextSearch"><form:input path="bookName" type="text" class="textStyle_130"/></td>
              
        </tr></table></td></tr>
        <tr><td class="rowSpace"></td></tr>
         <tr>
            <td><div id="divbtns">
                <input name="Search" type="submit" id="Search" title="Search"  value="Search"/>
                <input type="button" value="Reset" name="resetbutton" onClick="refreshPage()"/></div></td>
          </tr>
     
   <tr >
    <td class="headerText">B<span>OOK INFO</span></td>
  </tr>
    <tr>
    <td><table border="0" cellpadding="0" cellspacing="0" class="innerTable tab" >
        <tr>
          <td width="10%"  class="subHeaderText" align = "center">Book Type </td>
          <td width="10%" class="subHeaderText" align = "center"> Book ID </td>
          <td width="12%"  class="subHeaderText" align = "center"> Book Name </td>
		   <td width="10%"  class="subHeaderText" align = "center">Price</td>
          <td width="10%" class="subHeaderText" align = "center">Comments</td>
		  <td width="10%" class="subHeaderText" align = "center">No' of copies</td>
		   <!-- <td width="10%" class="subHeaderText" align = "center">you want to Buy ?</td> -->
		   <td width="12%" class="subHeaderText" align = "center">Add one more copy of Book</td>
		   <td width="10%" class="subHeaderText" align = "center">Delete</td>
		   
        </tr>
        
         <c:forEach items="${bookList}"  var="book" varStatus="loopCounter">
         <tr class="trClass${loopCounter.count}">
            
            <td align = "center"> ${book.bookType.description}</td>
            <td align = "center">${book.bookId}</td>

            <td align = "center">${book.bookName}</td>
            <td align = "center">${book.price}</td>
            <td align = "center">${book.comment}</td>
            <td align = "center" ><span id="nofCopy">${book.noOfCopies}</span></td>
           <%--  <td align = "center"><input type="button" value="Buy" title="buy" id="${book.id}" onclick="$(this).getNoOfCopies('id')" class ="buy"/></td> --%>
            <td align = "center"><input type="button" value="Add copy" title="Add copy" id="${book.id}" onclick="$(this).getAddNoOfCopies('id')"/></td>
            <td align = "center"><input type="button" value="Delete" title="Delete" onclick="javascript:deleteBook(${book.id})" /></td>
            
        </tr>
    	</c:forEach>
       
      </table></td>
  </tr>
  <tr><td class="rowSpace"></td></tr>
 <tr>
 <td><a href="login"><input type="button" value="Login" title="Login"/></a></td>
 </tr>
 <tr>
 <td><a href="bookdetails"><input type="button" value="Create" title="Create"/></a></td>
 </tr>
</table>

</form:form>

</body>
</html>
