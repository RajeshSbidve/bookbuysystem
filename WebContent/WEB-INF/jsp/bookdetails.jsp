<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Books</title>

<script language="javascript" src="jquery-1.8.3.js"></script>
<script language="javascript" src="jquery-ui.js"></script>
<script language="javascript" src="../../Javascript/SG_Validation.js"></script>
<script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>


<script language="javascript"
	src="/resources/Javascript/jquery-1.8.3.js"></script>
<script language="javascript" src="/resources/Javascript/jquery-ui.js"></script>
<script language="javascript" src="SG_Validation.js"></script>
<link
	href="<c:url value="resources/css/chegus-infotech-style-Hie.css"></c:url>"
	rel="stylesheet" />
<link href="<c:url value="resources/css/jquery-ui.css"></c:url>"
	rel="stylesheet" />
	
	
<script type="text/javascript">
$("document").ready(function(){
	
 $("input.add").click( function(){                          //  calling class=add  $- sign to define/access jquery |"."= class |  #= id

	 var last=$("#mytable").find("tr:last");  				// getting last <tr>
     var new1=last.clone();                   				// coping last tr with clone i.e creating one more <tr>  
     new1.attr("id", function()								// taking #id from tr id	
    		 {
    	 var newid=$(this).attr("id");          			// tr id=Dup0  
    	 var regIdMatch = /^(.+)(\d+)$/; 					// matching pattern .+ means anything including special chat string etc (\d) means number 
    	 var autoid=newid.match(regIdMatch);  	 			// newid=Dup0  Dup0	
         var res=parseInt(autoid[2])+1;						// Adding 1 to dup0		
    	 return autoid[1]+res; 								// return Dup1
    	 
    		 });
     
     	new1.find("select").each(function()
    		{
    		
    	 		$(this).attr("name",function(){               
    	 		var newid=$(this).attr("name");          //  bookdetails[0].bookType.id
    	 		var regIdMatch = /^(.+)(\d+)(.+)$/;		 // taking pattern
    	 		var autoid=newid.match(regIdMatch);      //  autoid=0     bookdetails[0].bookType.id,bookdetails[0].bookType.id	
    	        var res=parseInt(autoid[2])+1;           // adding 1 to 0 to 1  
    		 	var v=autoid[1]+res+autoid[3];           // bookdetails[1].bookType.id
    		 	return v;
    	 	});
    
			}).val('');
    	 
     	new1.find("input").each(function()
    	    	{
     			$(this).attr("name",function(){
    	    	var newid=$(this).attr("name");
    	    	var regIdMatch = /^(.+)(\d+)(.+)$/;
        	 	var autoid=newid.match(regIdMatch);  	 		
        	    var res=parseInt(autoid[2])+1;	
        	 	return autoid[1]+res+autoid[3];
    	    	 
    			}).val('');
    	    	});
     last.after(new1);
     
   
   });
});

</script>	


</head>



<body class="bodyClass">
	<c:url var="action" value="/save.html"></c:url>
	<s:form action="${action}" method="post" commandName="bookdetails"
		modelAttribute="bookdetails">
		<table border="0" cellpadding="0" cellspacing="0" class="mainTable">
			<tr>
				<td class="headerText"><span>BOOKS</span></td>
			</tr>
			<tr>
				<td class="subHeaderText"><h3>Book Details</h3></td>
			</tr>
			<tr>
				<td>
					<table border="0" cellpadding="0" cellspacing="0"
						class="innerTable">
						<tbody id="coun">
						</tbody>
						<tr>
							<td><fieldset>
									<legend>Book Details</legend>
									
									<table id="mytable" cellpadding="0" cellspacing="0" width="100%"
										class="noborder">
										<tbody id="cur">
											<tr>
												<td class="labelTextDetails">Book type</td>
												<td class="labelTextDetails">Book ID</td>
												<td class="labelTextDetails">Book Name</td>
												<td class="labelTextDetails">Price</td>
												<td class="labelTextDetails">Comments</td>
												<td class="labelTextDetails">No of Copies</td>
												<td><input type="button" value ="Add"  id="add" class="add" /></td>
											</tr>
 		 
											<tr id="Dup0">
											
												<td class="fieldText"><s:select path="bookdetails[0].bookType.id"
														name="select" class="dropdown_Free">
														<c:forEach items="${books}" var="book">
														<s:option value="${book.id}">${book.description}</s:option>
														</c:forEach>
													</s:select></td>
												<td class="fieldText"><s:input path="bookdetails[0].bookId"     id="bookid"/></td>
												<td class="fieldText"><s:input path="bookdetails[0].bookName"   id="bookname" /></td>
												<td class="fieldText"><s:input path="bookdetails[0].price" 		id="price"/></td>
												<td class="fieldText"><s:input path="bookdetails[0].comment" 	id="price"/></td>
												<td class="fieldText"><s:input path="bookdetails[0].noOfCopies" id="noofcopies"/></td>
												
											</tr>
											
										</tbody>
									</table>
								</fieldset></td>
						</tr>
					</table>
				</td>
			</tr>

			<tr>
				<td class="rowSpace"></td>
			</tr>
			
			<tr>
				<td class="Space"></td>
			</tr>
		</table>
		<input type="submit" value="Submit" /><a href="loginForm"><input type="button" value ="Cancel"/></a>
	</s:form>
</body>
</html>
