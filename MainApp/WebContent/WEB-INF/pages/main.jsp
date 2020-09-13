<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Main</title>

<link rel="stylesheet" type="text/css" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"> 
<link rel="stylesheet" type="text/css" href="styles/styleMain.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<link rel="sylesheet" href="font/Rimouski.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false"></script>

<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>


</head>
<body>

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-12"
				style="height: 10vh; background-color: #D6EAF8; border-bottom: 1px solid #2471A3;">
				<img id="image" class="image"
					src="${userBean.picture}" />
				<form method="POST" action="?action=logout">
					<div class="settings">
						<input type="submit" name="" value="">
					</div>
				</form>
				<p class="username">${userBean.numberOfLogin}</p>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-2"
				style="height: 90vh; background-color: #D6EAF8;">
				<p>Notifications</p>
				<div id="notifications" style="width: 100%; height: 82vh; overflow: scroll;"></div>
			</div>
			<div class="col-sm-7"
				style="height: 90vh; background-color: #CCD1D1;" id="col2">

				<table style="width: 100%;">
					<tr>
						<td><button class="backBtn" id="backBtn"></button></td>
						<td>
							<p style="text-align: center;">Posts</p>
						</td>
						<td>
							<button class="addBtn" id="addBtn" data-toggle="modal"
								data-target="#exampleModal">+</button>




							<div class="modal fade" id="exampleModal" tabindex="-1"
								role="dialog" aria-labelledby="exampleModalLabel"
								aria-hidden="true" style="height: 100vh; width:100vw; ">
								<div class="modal-dialog" role="document" style="width:70vw;">
									<div class="modal-content" style="width:50vw;">
										<div class="modal-header" style="height: 10vh; color: #2471A3;">

											<table style="width: 100%;">
												<tr style="height: 100%;">
													<td><h5 class="modal-title" id="exampleModalLabel"
															style="font-size: 1.5vw;">Add post</h5></td>
													<td></td>
													<td></td>
													<td>
														<button type="button" id="modalClose" class="closeBtn"
															data-dismiss="modal" aria-label="Close">X</button>
													</td>
												</tr>
											</table>
										</div>
										<div class="modal-body" style="height: 60vh; id="modalLoad">
											<div class=tab>
												<button class="tablinks" id="link">Link</button>
												<button class="tablinks" id="video">Video</button>
												<button class="tablinks" id="youtubeVideo">Youtube video</button>
												<button class="tablinks" id="pictures">Pictures</button>
											</div>
											<div id="bodyModal" class="tabcontent" style="overflow: scroll;"></div>
										</div>
										<div class="modal-footer" style="border: none;"></div>
									</div>
								</div>
							</div>
						</td>
					</tr>
				</table>
				<div id="pane" style="width: 100%; height: 82vh; overflow: scroll;">
					
				</div>
			</div>
			<div class="col-sm-3"
				style="height: 90vh; background-color: #D6EAF8;">
				<p>
					Weather forecast
					<p>
				
				<div>
					<div class="notification"></div>
					<div class="weather-container">
						<div class="weather-icon">
							<img src="icons/unknown.png" id="img1">
						</div>
						<div class="temperature-value">
							<p class="temperature-valueP" id="tempValue1">
								- °<span>C</span>
							</p>
						</div>
						<div class="temperature-description">
							<p class="temperature-descriptionP" id="tempDescription1">-</p>
						</div>
						<div class="location">
							<p class="locationP" id="location1">-</p>
						</div>
					</div>
				</div>
				<br />
				<div>
					<div class="notification"></div>
					<div class="weather-container">
						<div class="weather-icon">
							<img src="icons/unknown.png" id="img2">
						</div>
						<div class="temperature-value">
							<p class="temperature-valueP" id="tempValue2">
								- °<span>C</span>
							</p>
						</div>
						<div class="temperature-description">
							<p class="temperature-descriptionP" id="tempDescription2">-</p>
						</div>
						<div class="location">
							<p class="locationP" id="location2">-</p>
						</div>
					</div>
				</div>
				<br />
				<div>
					<div class="notification"></div>
					<div class="weather-container">
						<div class="weather-icon">
							<img src="icons/unknown.png" id="img3">
						</div>
						<div class="temperature-value">
							<p class="temperature-valueP" id="tempValue3">
								- °<span>C</span>
							</p>
						</div>
						<div class="temperature-description">
							<p class="temperature-descriptionP" id="tempDescription3">-</p>
						</div>
						<div class="location">
							<p class="locationP" id="location3">-</p>
						</div>
					</div>
				</div>
				<input type="text" id="country" value="${userBean.user.country}"
					hidden="true">
			</div>
		</div>
	</div>
	</div>
	<script type="text/javascript">
	
	
	
	
	
	
	var slideIndex = 1;
	
	function showBegin(){
		var x = document.getElementsByClassName("mySlides");
		for(var i=0; i < x.length; i++){
			plusDivs(1,x[i].name);
		}
	}
	
	
	function plusDivs(n,name) {
	  showDivs(slideIndex += n,name);
	}

	function showDivs(n,name) {
	  var i;
	  var x = document.getElementsByName(name);
	  if(typeof x[0] !== 'undefined'){
		  if (n > x.length) {slideIndex = 1}
		  if (n < 1) {slideIndex = x.length}
		  for (i = 0; i < x.length; i++) {
		    x[i].style.display = "none";  
		  }
		  x[slideIndex-1].style.display = "block"; 
	  } 
	}
		
	const categories=[];
	
	function getCategories(){
		var request = new XMLHttpRequest();
		request.onreadystatechange = function() {
			if ((request.readyState == 4) && (request.status == 200)) {
				var results = JSON.parse(request.responseText);
				var i=0;
				results.forEach(category => {
					categories[i]=category.name;
					i++;
				});
				
			}
		};
		request.open("GET", "rest/categories/getItemCategories", true);
		request.send(null);
		
	}
	getCategories();
	
	
	
	const notifications=document.getElementById("notifications");
	const pane=document.getElementById("pane");
	const backBtn=document.getElementById("backBtn");
	const addBtn=document.getElementById("addBtn");
	
	function showItems(){
		var request = new XMLHttpRequest();
		request.onreadystatechange = function() {
			
			pane.innerHTML="<div class=\"d-flex justify-content-center\"><div class=\"spinner-border text-primary\" style=\"width: 3rem; height: 3rem;\" role=\"status\"><span class=\"sr-only\">Loading...</span> </div></div>";
			notifications.innerHTML="<div class=\"d-flex justify-content-center\"><div class=\"spinner-border text-primary\" style=\"width: 3rem; height: 3rem; \" role=\"status\"><span class=\"sr-only\">Loading...</span> </div></div>";
			
			backBtn.style.visibility = 'hidden';
			
			addBtn.style.visibility = 'visible';
			
			
			if ((request.readyState == 4) && (request.status == 200)) {
				var results = JSON.parse(request.responseText);
				pane.innerHTML="";
				notifications.innerHTML="";
				results.forEach(result =>{
					var description="";
					var link="";
					var youtubeVideoUrl="";
					var videoSrc="";
					var imagesSrc="";
					var end="";
					var address="";
					var categories="";
					
					var facebook="<td style=\"width:5%;\"></td>";
					var twiter="<td style=\"width:5%;\"></td>";
					
					if(!! result.emergency){
						notifications.innerHTML+="<div class=\"notificationBox\">"
						+"<item>"
						+"<p class=\"p2\">"+result.title+"</p>"
						+"<h6 style=\"color: #2471A3; margin:0; font-size:1.5vw;\">"+result.categories+"</h6>"
						+"<a name=\"details\" id=\""+result.id+"\" style=\"color: #2471A3; float:right; cursor: pointer; font-size:1.5vw;\">Details</a>"
						+"</item></div>";
						
					}
					
					if(!! result.description){
						description="<p style=\"text-align:left;\">"+result.description+"</p>"
							+"<br /><br />";
					}
					if(!! result.link){
						link="<h6 style=\"color: #2471A3; font-size:1.5vw;\">You can read more at:</h6>"
							+"<a href=\""+result.link+"\" style=\"color: #154360; font-size:1.5vw; \">"+result.link+"</a>"
							+"<br /><br />";
							
						facebook="<td style=\"width:5%;\"><a href=\"https://www.facebook.com/sharer/sharer.php?u="+result.link+"\" class=\"icoFacebook\" title=\"Facebook\" target=\"_blank\"><i class=\"fa fa-facebook\"></i></a></td>";
						twiter="<td style=\"width:5%;\"><a href=\"https://twitter.com/intent/tweet?text="+result.link+"\" class=\"icoTwitter\" title=\"Twitter\" target=\"_blank\"><i class=\"fa fa-twitter\"></i></a></td>"
					}
					
					if(!!result.youtubeVideoUrl){
						youtubeVideoUrl="<iframe style=\"width: 60%; height: 60%; margin-left: 10vw\" src=\""+result.youtubeVideoUrl+"\"> </iframe>"
						+"<br /><br />";
						
						facebook="<td style=\"width:5%;\"><a href=\"https://www.facebook.com/sharer/sharer.php?u="+result.youtubeVideoUrl+"\" class=\"icoFacebook\" title=\"Facebook\" target=\"_blank\"><i class=\"fa fa-facebook\"></i></a></td>";
						twiter="<td style=\"width:5%;\"><a href=\"https://twitter.com/intent/tweet?text="+result.youtubeVideoUrl+"\" class=\"icoTwitter\" title=\"Twitter\" target=\"_blank\"><i class=\"fa fa-twitter\"></i></a></td>";
					}
					if(!!result.videoSrc){
						videoSrc="<video style=\"width: 60%; height: 60%; margin-left: 10vw\" controls preload=\"none\">"
						+"<source src=\""+result.videoSrc+"\" type=\"video/mp4\">"
						+"<source src=\""+result.videoSrc+"\" type=\"video/ogg\">"
						+"Your browser does not support HTML video. </video>"
						+"<br /><br />";
					}
					
					if(!!result.imagesSrc){
						
						var imagesSrcList = result.imagesSrcList;
						var pom="";
						imagesSrcList.forEach(s => {
							pom+="<img class=\"mySlides\" src=\""+s+"\" style=\"width: 100%; height: 100%;\" name=\""+result.id+"\">"
						});
						imagesSrc="<div class=\"w3-content w3-display-container\" style=\"width: 25vw; height: 25vw; margin-left: 10vw\">"
							+pom
							+"<button class=\"w3-button w3-black w3-display-left\" name=\"left\" id=\""+result.id+"\" >&#10094;</button>"
							+"<button class=\"w3-button w3-black w3-display-right\" name=\"right\" id=\""+result.id+"\" >&#10095;</button>"
							+"</div><br /><br />";
					}
					
					if(!!result.id){
						end+="<table><tr>"
						+"<td style=\"width:5%;\"><img id=\"image\" class=\"image\" style=\"margin-left:1vw;\" src=\""+result.picture+"\" /></td>"
						+"<td style=\"width:80%; text-align: left; vertical-align:center;\"><h6 style=\"color: #2471A3; font-size:1.5vw;\">"+result.pubDate+"</h6></td>"
						+twiter
						+facebook
						+"<td style=\"width:5%;\"><button class=\"commentBtn\" name=\"commentBtn\" id=\""+result.id+"\" ></button></td>"
						+"</tr></table>";
					}else{
						end+="<br /><h6 style=\"color: #2471A3; margin-left:1vw; font-size:1.5vw;\">"+result.pubDate+"</h6>"
					}
					
					
					if(!!result.address){
						address="<h6 style=\"color: #2471A3; font-size:1.5vw;\">Location: "+result.address+"</h6>";
					}
					if(!!result.categories){
						categories="<h6 style=\"color: #2471A3; font-size:1.5vw;\">Category: "+result.categories+"</h6><br/>";
					}
					
				
					
					pane.innerHTML+="<div class=\"postBox\"><item><p class=\"p1\">"+result.title+"</p>"
					+description
					+link
					+youtubeVideoUrl
					+videoSrc
					+imagesSrc
					+address
					+categories
					+end
					+"</item></div>";
					showBegin();
					
					
					
					const details=document.getElementsByName("details");
					details.forEach(d => {
						d.addEventListener("click",function(){
							showComents(d.id);
					    });	
					});
					
					
					
					const left=document.getElementsByName("left");
					const right=document.getElementsByName("right");
					const commentBtn=document.getElementsByName("commentBtn");
					
					commentBtn.forEach(button => {
						button.addEventListener("click",function(){
							showComents(button.id);
					    });	
					});
					
					
					left.forEach(button => {
						button.addEventListener("click",function(){
							plusDivs(-1,button.id);
					    });	
					});
					
					right.forEach(button => {
						button.addEventListener("click",function(){
							plusDivs(1,button.id);
					    });	
					});
				});
				
				
			}
		};
		request.open("GET", "rest/item/", true);
		request.send(null);
	}
	
	
	
	showItems();
	
	
	
	
	

	
	
	
	
	
	
			function showComents(id){
				var request = new XMLHttpRequest();
				request.onreadystatechange = function() {
					if ((request.readyState == 4) && (request.status == 200)) {
						var result = JSON.parse(request.responseText);
						
						pane.innerHTML="";
						
						
						backBtn.style.visibility = 'visible';
						addBtn.style.visibility = 'hidden';
						
						var description="";
						var link="";
						var youtubeVideoUrl="";
						var videoSrc="";
						var imagesSrc="";
						var end="";
						var address="";
						var categories="";
						if(!! result.description){
							description="<h6 style=\"color: #2471A3;\">Description</h6>"
								+"<br />"
								+"<p style=\"text-align:left; font-size:1vw;\">"+result.description+"</p>"
								+"<br /><br />";
						}
						if(!! result.link){
							link="<h6 style=\"color: #2471A3;\">You can read more at:</h6>"
								+"<a href=\""+result.link+"\" style=\"color: #154360;\">"+result.link+"</a>"
								+"<br /><br />";
						}
						
						if(!!result.youtubeVideoUrl){
							youtubeVideoUrl="<iframe style=\"width: 60%; height: 60%; margin-left: 10vw\" src=\""+result.youtubeVideoUrl+"\"> </iframe>"
							+"<br /><br />";
						}
						if(!!result.videoSrc){
							videoSrc="<video style=\"width: 60%; height: 60%; margin-left: 10vw\" controls preload=\"none\">"
							+"<source src=\""+result.videoSrc+"\" type=\"video/mp4\">"
							+"<source src=\""+result.videoSrc+"\" type=\"video/ogg\">"
							+"Your browser does not support HTML video. </video>"
							+"<br /><br />";
						}
						
						if(!!result.imagesSrc){
							
							var imagesSrcList = result.imagesSrcList;
							var pom="";
							imagesSrcList.forEach(s => {
								pom+="<img class=\"mySlides\" src=\""+s+"\" style=\"width: 100%; height: 100%;\" name=\""+result.id+"\">"
							});
							imagesSrc="<div class=\"w3-content w3-display-container\" style=\"width: 60%; height: 60%; margin-left: 10vw\">"
								+pom
								+"<button class=\"w3-button w3-black w3-display-left\" name=\"left\" id=\""+result.id+"\" >&#10094;</button>"
								+"<button class=\"w3-button w3-black w3-display-right\" name=\"right\" id=\""+result.id+"\" >&#10095;</button>"
								+"</div><br /><br />";
						}
						
						if(!!result.id){
							end+="<table><tr>"
							+"<td style=\"width:5%;\"><img id=\"image\" class=\"image\" style=\"margin-left:1vw;\" src=\""+result.picture+"\" /></td>"
							+"<td style=\"text-align: left; vertical-align:center;\"><h6 style=\"color: #2471A3;\">"+result.pubDate+"</h6></td>"
							+"<td></td><td></td>"
							+"</tr></table>";
						}else{
							end+="<br /><h6 style=\"color: #2471A3;\">"+result.pubDate+"</h6>"
						}
						
						if(!!result.address){
							address="<h6 style=\"color: #2471A3;\">Location: "+result.address+"</h6>";
						}
						if(!!result.categories){
							categories="<h6 style=\"color: #2471A3;\">Category: "+result.categories+"</h6><br/>";
						}
						
						
						
						var comments="";
						
						var request1 = new XMLHttpRequest();
						request1.onreadystatechange = function() {
							if ((request1.readyState == 4) && (request1.status == 200)) {
								var result1 = JSON.parse(request1.responseText);
								
								
								
								result1.forEach(com =>{
									var text="";
									var picture="";
									
									if(!!com.text){
										
										var id1="show-more"+com.id;
										var id2="show-less"+com.id;
										
										text="<p style=\"text-align:left; font-size:1vw;\">"+com.text+"</p>"
									}
									if(!!com.imageSrc){
										picture="<img src=\""+com.imageSrc+"\" style=\"width: 50%; height: 50%;\" >";
									}
									
									comments+="<div class=\"commentBox\">"
									+"<table style=\"height: auto; width: 100%;\"><tr>"
									+"<td style=\"height: auto; width: 30%; vertical-align:top;\"><img id=\"image\" class=\"image\" style=\" margin:0; margin-left:1vw;\" src=\""+com.picture+"\" /></td>"
									+"<td style=\"height: auto; width: 70%;\">"
									+text
									+"<br/>"
									+picture
									+"</td>"
									+"</tr></table>"
									+"</div>";
									
									
								
									
								});
								comments+="<div class=\"newCommentBox\">"
									+"<table style=\"width:100% height:100%;\">"
									+"<tr style=\"width:100% height:100%;\">"
									+"<td style=\"height:100%; width:5%;  vertical-align:center;\">"
									+"<img id=\"image\" class=\"image\" style=\"margin-top:0; margin-left:0.5vw;\" src=\"${userBean.picture}\" />"
									+"</td>"
									+"<td style=\"height:100%; width:50%; vertical-align:center;\">"
									+"<input type=\"text\" name=\"text\" id=\"text\" style=\"width:90%; height:100%; margin:0; margin-left:1vw;  font-size:1,5vw;\">"
									+"</td>"
									+"<td style=\"height:100%; width:40%; \">"
									+"<div class=\"fileinputs\">"
									+"<input id=\"realFile\" type=\"file\" class=\"file1\" />"
									+"	<div class=\"fakefile\">"
									+"		<input id=\"putText\"/><img style=\"height:auto; width:10%;\" src=\"styles/spajalica.png\" />"
									+"	</div></div>"
									+"</td>"
									+"<td style=\"height:100%; width:5%;\">"
									+"<button class=\"saveCommentBtn\" name=\"saveCommentBtn\" id=\"saveCommentBtn\" ></button>"
									+"</td>"
									+"</tr>"
									+"</table>"
									+"</div>";
							}
							
							
							
							pane.innerHTML="<div class=\"postBox\"><item><p class=\"p1\">"+result.title+"</p>"
							+description
							+link
							+youtubeVideoUrl
							+videoSrc
							+imagesSrc
							+address
							+categories
							+end
							+"</item></div>"
							+comments;
							
							showBegin();
							const left=document.getElementsByName("left");
							const right=document.getElementsByName("right");
							
							
							left.forEach(button => {
								button.addEventListener("click",function(){
									plusDivs(-1,button.id);
							    });	
							});
							
							right.forEach(button => {
								button.addEventListener("click",function(){
									plusDivs(1,button.id);
							    });	
							});
							
							
							const realFile=document.getElementById("realFile");
							const putText=document.getElementById("putText");
							if(realFile != null){
								realFile.addEventListener("change",function(){
									putText.value=realFile.value;
							    });
							}
							
							
							const saveCommentBtn=document.getElementById("saveCommentBtn");
							if(saveCommentBtn != null){
								saveCommentBtn.addEventListener("click",function(){
									
									
									var text=document.getElementById("text").value;
									var file =document.getElementById("realFile").files[0];
									var itemId=id;
									var formdata = new FormData();
									
									var isThereFile="";
									if(typeof file !== 'undefined'){
										isThereFile="yes"
									}else{
										isThereFile="no"
									}
									
							    	formdata.append("text",text);
							        formdata.append("file", file);
							        formdata.append("itemId", itemId);
							        formdata.append("isThereFile", isThereFile);
							        var xhr = new XMLHttpRequest();    
							        xhr.open("POST","UploadCommentController", true);

							        xhr.send(formdata);
							        xhr.onload = function(e) {

							            if (this.status == 200) {

							            	showComents(itemId);

							            }

							        };  
									
									
									
							    });
							}
							
								
							
							
						};
						request1.open("GET", "rest/item/comments/"+id, true);
						request1.send(null);
						
						
					}
					
					
				};
				request.open("GET", "rest/item/"+id, true);
				request.send(null);
				
				
			}
	
	
			
			
			backBtn.addEventListener("click",function(){
				pane.innerHTML="";
				notifications.innerHTML="";
				showItems();
		    });	
			
		    const img1=document.getElementById("img1");
		    const img2=document.getElementById("img2");
		    const img3=document.getElementById("img3");
		    const tempValue1=document.getElementById("tempValue1");
		    const tempValue2=document.getElementById("tempValue2");
		    const tempValue3=document.getElementById("tempValue3");
		    const tempDescription1=document.getElementById("tempDescription1");
		    const tempDescription2=document.getElementById("tempDescription2");
		    const tempDescription3=document.getElementById("tempDescription3");
		    const location1=document.getElementById("location1");
		    const location2=document.getElementById("location2");
		    const location3=document.getElementById("location3");
		   
		    const KELVIN =273;
		    let city;
		    
		    const weather1 ={
		    		temperature : {
		    			value : undefined,
		    			unit: "celsius"
		    		},
		    		description : "111",
		    		iconId : "01d",
		    		city : "111",
		    		country : "111"
		    };
		    const weather2 ={
		    		temperature : {
		    			value : undefined,
		    			unit: "celsius"
		    		},
		    		description : "222",
		    		iconId : "01n",
		    		city : "2222",
		    		country : "2222"
		    };
		    const weather3 ={
		    		temperature : {
		    			value : undefined,
		    			unit: "celsius"
		    		},
		    		description : "3333",
		    		iconId : "02d",
		    		city : "333",
		    		country : "333"
		    };
		    
		    
		    
		    let city1="";
		    let city2="";
		    let city3="";
		    
		    let userInfo=""+document.getElementById("country").value;
		    let info =[];
		    info = userInfo.split("-");
		    let country=""+info[0];
		    let region=""+info[1];
		    changeCitys(country,region,"cb");
		   
		    
		    
		    
		    
		    
		    
		    
		    function changeCitys(country,region,callback) {
				var e = document.createElement('script');
		        e.src =  "http://battuta.medunes.net/api/city/"+country+"/search/?region="+region+"&key=87542d7cd45602be618c58b8e941b6d8&callback=cb";
		        document.body.appendChild(e);
		        window[callback] = (data) => {
		        	initializeCity(data);
		        }
			}
		    function initializeCity(cityData){
		    	city = cityData;
		    	let cityArray=[];
		    	let i=0;
				city.forEach(city => {cityArray[i]=""+city.city; i+=1;});
		    	city1=""+cityArray[0];
		    	city2=""+cityArray[1];
		    	city3=""+cityArray[2];
			    getWeather(city1,city2,city3);
			}
		    
		    function getWeather(city1,city2,city3){
		    	fetch("http://api.openweathermap.org/data/2.5/weather?q="+city1+"&APPID=2019277aae1e5e52773c04ac47810e6d")
		    	.then(function(response){
		    		let data=response.json();
		    		return data;
		    	})
		    	.then(function(data){
		    		weather1.temperature.value = Math.floor(data.main.temp - KELVIN);
		    		weather1.description=data.weather[0].description;
		    		weather1.iconId=data.weather[0].icon;
		    		weather1.city=data.name;
		    		weather1.country=data.sys.country;
		    	}).then(function(){
		    		displayWeather1();
		    	}).catch(err => console.log("Error:", err));;
		    	
		    	
		    	fetch("http://api.openweathermap.org/data/2.5/weather?q="+city2+"&APPID=2019277aae1e5e52773c04ac47810e6d")
		    	.then(function(response){
		    		let data=response.json();
		    		return data;
		    	})
		    	.then(function(data){
		    		weather2.temperature.value = Math.floor(data.main.temp - KELVIN);
		    		weather2.description=data.weather[0].description;
		    		weather2.iconId=data.weather[0].icon;
		    		weather2.city=data.name;
		    		weather2.country=data.sys.country;
		    	}).then(function(){
		    		displayWeather2();
		    	}).catch(err => console.log("Error:", err));;
		    	
		    	fetch("http://api.openweathermap.org/data/2.5/weather?q="+city3+"&APPID=2019277aae1e5e52773c04ac47810e6d")
		    	.then(function(response){
		    		let data=response.json();
		    		return data;
		    	})
		    	.then(function(data){
		    		weather3.temperature.value = Math.floor(data.main.temp - KELVIN);
		    		weather3.description=data.weather[0].description;
		    		weather3.iconId=data.weather[0].icon;
		    		weather3.city=data.name;
		    		weather3.country=data.sys.country;
		    	}).then(function(){
		    		displayWeather3();
		    	}).catch(err => console.log("Error:", err));;
		    }
		    
		    
		    
		  
		    
		    
		    tempValue1.addEventListener("click",function(){
		    	if(weather1.temperature.value === undefined) return;
		    	if(weather1.temperature.unit === "celsius"){
		    		let fahrenheit = celsiusToFahrenheit(weather1.temperature.value);
		    		fahrenheit=Math.floor(fahrenheit);
		    		tempValue1.innerHTML = ""+fahrenheit+" °<span>F</span>";
		    		weather1.temperature.unit = "fahrenheit";
		    	}else{
		    		tempValue1.innerHTML = ""+weather1.temperature.value+" °<span>C</span>";
		    		weather1.temperature.unit = "celsius";
		    	}
		    });
		    
		    
		    tempValue2.addEventListener("click",function(){
		    	if(weather2.temperature.value === undefined) return;
		    	if(weather2.temperature.unit === "celsius"){
		    		let fahrenheit = celsiusToFahrenheit(weather2.temperature.value);
		    		fahrenheit=Math.floor(fahrenheit);
		    		tempValue2.innerHTML = ""+fahrenheit+" °<span>F</span>";
		    		weather2.temperature.unit = "fahrenheit";
		    	}else{
		    		tempValue2.innerHTML = ""+weather2.temperature.value+" °<span>C</span>";
		    		weather2.temperature.unit = "celsius";
		    	}
		    });
		    
		    tempValue3.addEventListener("click",function(){
		    	if(weather3.temperature.value === undefined) return;
		    	if(weather3.temperature.unit === "celsius"){
		    		let fahrenheit = celsiusToFahrenheit(weather3.temperature.value);
		    		fahrenheit=Math.floor(fahrenheit);
		    		tempValue3.innerHTML = ""+fahrenheit+" °<span>F</span>";
		    		weather3.temperature.unit = "fahrenheit";
		    	}else{
		    		tempValue3.innerHTML = ""+weather3.temperature.value+" °<span>C</span>";
		    		weather3.temperature.unit = "celsius";
		    	}
		    });
		    
		function celsiusToFahrenheit(temperature){
			return (temperature * 9/5)+32;
		}
		    
		    
		    
		function displayWeather1(){
			img1.src="icons/"+weather1.iconId+".png";
			tempValue1.innerHTML = ""+weather1.temperature.value+" °<span>C</span>";
			tempDescription1.innerHTML = weather1.description;
			location1.innerHTML = ""+weather1.city+", "+weather1.country;
		} 
		
		function displayWeather2(){
			img2.src="icons/"+weather2.iconId+".png";
			tempValue2.innerHTML = ""+weather2.temperature.value+" °<span>C</span>";
			tempDescription2.innerHTML = weather2.description;
			location2.innerHTML = ""+weather2.city+", "+weather2.country;
		} 
		function displayWeather3(){
			img3.src="icons/"+weather3.iconId+".png";
			tempValue3.innerHTML = ""+weather3.temperature.value+" °<span>C</span>";
			tempDescription3.innerHTML = weather3.description;
			location3.innerHTML = ""+weather3.city+", "+weather3.country;
		} 
		
		
		    
		const link=document.getElementById("link");
		const video=document.getElementById("video");
		const youtubeVideo=document.getElementById("youtubeVideo");
		const pictures=document.getElementById("pictures");
		const body=document.getElementById("bodyModal");
		const modalClose=document.getElementById("modalClose");
		var type="";
			
		
		
		$(document).ready(function () {
         	function lazyLoadGoogleMaps() {
         	    $.getScript("https://maps.googleapis.com/maps/api/js?key=AIzaSyDOcdXV92aiZol4zMzLrjmC0LtVlEMMN14&callback=googleMapsLoaded")
         	        .done(function (script, textStatus) {
         	            alert("Google maps loaded successfully");
         	        })
         	        .fail(function (jqxhr, settings, ex) {
         	            alert("Could not load Google Maps: ", ex);
         	        });
         	    }
         	});


         	function googleMapsLoaded() {
         	    alert("Done!");
         	}
		 
         	
         	
         	modalClose.addEventListener("click",function(){
         		body.innerHTML="";
		    });	
		
         	addBtn.addEventListener("click",function(){
         		loadLink();
		    });	
		
		
		
		$('.modal').on('shown.bs.modal', function () {
			loadLink();
			});
		
		 link.addEventListener("click",function(){
			 loadLink();
				    });	
		 
		 
		 var expanded = false;

		 function showCheckboxes() {
		   var checkboxes = document.getElementById("checkboxes");
		   if (!expanded) {
		     checkboxes.style.display = "block";
		     expanded = true;
		   } else {
		     checkboxes.style.display = "none";
		     expanded = false;
		   }
		 }
		 
		 function loadLink(){
			 type = "link";
			 
			 var options="";
			 var i=1;
			 categories.forEach(category => {
				 options+="  <label for=\""+i+"\"><input type=\"checkbox\" id=\""+i+"\" value=\""+category+"\" />"+category+"</label>";
				 i++;
			 });
			 
		    	body.innerHTML="<form id=\"forma\" enctype=\"multipart/form-data\">"
		    		+"<p style=\"height:4%; width:100%; font-size:1vw; color:#2471A3; text-align: left;\">Title: *</p>"
		    		+"<input type=\"text\" id=\"title\" style=\"height:8%; width:100%; font-size:1vw; color:#2471A3;\">"
					+"<br></br>"
					+"<p style=\"height:4%; width:100%; font-size:1vw; color:#2471A3; text-align: left;\">Description:</p>"
					+"<textarea id=\"description\" style=\"height:16%; width:100%; font-size:1vw; color:#2471A3;\"></textarea>"
					+"<br></br>"
					+"<p style=\"height:4%; width:100%; font-size:1vw; color:#2471A3; text-align: left;\">Link: *</p>"
					+"<input type=\"text\" id=\"url\" style=\"height:8%; width:100%; font-size:1vw; color:#2471A3;\">"
					+"<br></br>"
					+"<p style=\"height:4%; width:100%; font-size:1vw; color:#2471A3; text-align: left;\">Choose categorie: *</p>"
					+"<div class=\"multiselect\">"
				    +"<div class=\"selectBox\" id=\"selectBox\">"
				    +" <select><option>Select an option</option></select>"
				    +" <div class=\"overSelect\"></div></div>"
				    +"<div id=\"checkboxes\">"
				    +options
				    +"</div></div>"
					+"<br></br>"
					+"<div id=\"map\" style=\"width:100%; height:40vh;\"></div>"
					+"<br></br>"
					+"<input type=\"button\" class=\"btn btn-primary\" id=\"saveButton\" style=\"float:right;\" value=\"Save\"></input>"
					+"<input type=\"checkbox\" id=\"emergencyWarning\" />Emergency warning?</label>"
					+"<input type=\"hidden\" id=\"address\" value=\"\">"
					+"</form>";
					
					
					const selectBox=document.getElementById("selectBox");
					selectBox.addEventListener("click",function(){showCheckboxes()});
					
					
					
					  const saveButton=document.getElementById("saveButton");
					    
					    
					    saveButton.addEventListener("click",function(){
					    	numOfPictures=0;
					    	var msg= document.getElementById("msg");
					    	var link = document.getElementById("url").value;
					    	var title=document.getElementById("title").value;
					    	 var categories="";
						    	for(var j=(i-1);j>0;j--){
									var idElement=""+j;
									var element= document.getElementById(j);
									if(element.checked){
										categories+=element.value+", ";
									}
								}
						    if(!checkTitle(title)){
						    	alert("You must enter title!");
						    }else if(!checkCategories(categories)){
						    	alert("You must choose categories!");
						    }else if(checkLink(link)){
						    	var description=document.getElementById("description").value;
						    	var address = document.getElementById("address").value;
						    	var formdata = new FormData();
						    	formdata.append("type",type);
						        formdata.append("title", title);
						        formdata.append("description", description);
						        formdata.append("link", link);
						        formdata.append("address", address);
						        
						       
						        formdata.append("categories", categories);
						        
						        
						        var emergencyWarning="";
						        var checkBox= document.getElementById("emergencyWarning");
						        if(checkBox.checked){
						        	emergencyWarning="yes";
								}else{
									emergencyWarning="no";
								}
						        formdata.append("emergencyWarning", emergencyWarning);
						        
						        var xhr = new XMLHttpRequest();    
						        xhr.open("POST","UploadImageController", true);

						        xhr.send(formdata);
						        xhr.onload = function(e) {

						            if (this.status == 200) {

						               document.getElementById("modalClose").click();
								       showItems();
						            }

						        }; 
						    	
					    	}
					    	
					    	
					    	 
					    });
					
		    	var mapCon = document.getElementById("map");
	            google.maps.event.addDomListener(window, 'load', myMap(mapCon));
		 }
		 
		 
		 
		 
		 youtubeVideo.addEventListener("click",function(){
				type = "youtubeVideo";
				var options="";
				 var i=1;
				 categories.forEach(category => {
					 options+="  <label for=\""+i+"\"><input type=\"checkbox\" id=\""+i+"\" value=\""+category+"\" />"+category+"</label>";
					 i++;
				 });
		    	body.innerHTML="<form id=\"forma\" enctype=\"multipart/form-data\">"
		    		+"<p style=\"height:4%; width:100%; font-size:1vw; color:#2471A3; text-align: left;\">Title: *</p>"
					+"<input type=\"text\" id=\"title\" style=\"height:8%; width:100%; font-size:1vw; color:#2471A3;\">"
					+"<br></br>"
					+"<p style=\"height:4%; width:100%; font-size:1vw; color:#2471A3; text-align: left;\">Description:</p>"
					+"<textarea id=\"description\" style=\"height:16%; width:100%; font-size:1vw; color:#2471A3;\"></textarea>"
					+"<br></br>"
					+"<p style=\"height:4%; width:100%; font-size:1vw; color:#2471A3; text-align: left;\">Url: *</p>"
					+"<input type=\"text\" id=\"url\" style=\"height:8%; width:100%; font-size:1vw; color:#2471A3;\">"
					+"<br></br>"
					+"<p style=\"height:4%; width:100%; font-size:1vw; color:#2471A3; text-align: left;\">Choose categorie: *</p>"
					+"<div class=\"multiselect\">"
				    +"<div class=\"selectBox\" id=\"selectBox\">"
				    +" <select><option>Select an option</option></select>"
				    +" <div class=\"overSelect\"></div></div>"
				    +"<div id=\"checkboxes\">"
				    +options
				    +"</div></div>"
					+"<br></br>"
					+"<div id=\"map\" style=\"width:100%; height:40vh;\"></div>"
					+"<br></br>"
					+"<input type=\"button\" class=\"btn btn-primary\" id=\"saveButton\" style=\"float:right;\" value=\"Save\"></input>"
					+"<input type=\"checkbox\" id=\"emergencyWarning\" />Emergency warning?</label>"
					+"<input type=\"hidden\" id=\"address\" value=\"\">"
					+"</form>";
					
					const selectBox=document.getElementById("selectBox");
					selectBox.addEventListener("click",function(){showCheckboxes()});
					
				    const saveButton=document.getElementById("saveButton");
				    
				    
				    saveButton.addEventListener("click",function(){
				    	
				    	var url = document.getElementById("url").value;
				    	var title=document.getElementById("title").value;
				    	var categories="";
				    	for(var j=(i-1);j>0;j--){
							var idElement=""+j;
							var element= document.getElementById(j);
							if(element.checked){
								categories+=element.value+", ";
							}
						}
				    	if(!checkTitle(title)){
					    	alert("You must enter title!");
					    }else if(!checkCategories(categories)){
					    	alert("You must choose categories!");
					    }else if(checkYouTubeURL(url)){
				    		
				              const regExp = /^.*((youtu.be\/)|(v\/)|(\/u\/\w\/)|(embed\/)|(watch\?))\??v?=?([^#\&\?]*).*/;
				              const match = url.match(regExp);
				              const lastPart = (match && match[7].length == 11) ? match[7] : false;
				              url = 'https://www.youtube.com/embed/' + lastPart; // + "?autoplay=1";
				    		  
				              
						      var description=document.getElementById("description").value;
						      var address = document.getElementById("address").value;
						      
						      
						      var formdata = new FormData();
						    	formdata.append("type",type);
						        formdata.append("title", title);
						        formdata.append("description", description);
						        formdata.append("url", url);
						        formdata.append("address", address);
						        
						        
						        formdata.append("categories", categories);
						        
						        var emergencyWarning="";
						        var checkBox= document.getElementById("emergencyWarning");
						        if(checkBox.checked){
						        	emergencyWarning="yes";
								}else{
									emergencyWarning="no";
								}
						        formdata.append("emergencyWarning", emergencyWarning);
						        
						        var xhr = new XMLHttpRequest();    
						        xhr.open("POST","UploadImageController", true);

						        xhr.send(formdata);
						        xhr.onload = function(e) {

						            if (this.status == 200) {

						            	document.getElementById("modalClose").click();
									       showItems();

						            }

						        }; 
				        }
				    	
				    	
				    	
				    	
				    	 
				    });	
					
		    	var mapCon = document.getElementById("map");
	            google.maps.event.addDomListener(window, 'load', myMap(mapCon));
		    });	
		 
		 video.addEventListener("click",function(){
			 	type = "video";
			 	var options="";
				 var i=1;
				 categories.forEach(category => {
					 options+="  <label for=\""+i+"\"><input type=\"checkbox\" id=\""+i+"\" value=\""+category+"\" />"+category+"</label>";
					 i++;
				 });
		    	body.innerHTML="<form id=\"forma\" enctype=\"multipart/form-data\">"
		    		+"<p style=\"height:4%; width:100%; font-size:1vw; color:#2471A3; text-align: left;\">Title: *</p>"
					+"<input type=\"text\" id=\"title\" style=\"height:8%; width:100%; font-size:1vw; color:#2471A3;\">"
					+"<br></br>"
					+"<p style=\"height:4%; width:100%; font-size:1vw; color:#2471A3; text-align: left;\">Description:</p>"
					+"<textarea id=\"description\" style=\"height:16%; width:100%; font-size:1vw; color:#2471A3;\"></textarea>"
					+"<br></br>"
					+"<table style=\"width: 100%; height: 5vh; border: 1px; border-color: #2471A3;\">"
					+"<tr>"
					+"<td style=\"width:25%\">"
					+"<p style=\"height:4%; width:100%; font-size:1vw; color:#2471A3; text-align: left;\">Choose a video: *</p>"
					+"</td>"
					+"<td style=\"width:75%\">"
					+"<input type=\"file\" style=\"width:100%; height:50%;\" id=\"fileVideo\" name=\"file\" class=\"file\" accept=\"video/*\"/>"
					+"</td>"
					+"</tr>"
					+"</table>"
					+"<br></br>"
					+"<p style=\"height:4%; width:100%; font-size:1vw; color:#2471A3; text-align: left;\">Choose categorie: *</p>"
					+"<div class=\"multiselect\">"
				    +"<div class=\"selectBox\" id=\"selectBox\">"
				    +" <select><option>Select an option</option></select>"
				    +" <div class=\"overSelect\"></div></div>"
				    +"<div id=\"checkboxes\">"
				    +options
				    +"</div></div>"
					+"<br></br>"
					+"<div id=\"map\" style=\"width:100%; height:40vh;\"></div>"
					+"<br></br>"
					+"<input type=\"button\" class=\"btn btn-primary\" id=\"saveButton\" style=\"float:right;\" value=\"Save\"></input>"
					+"<input type=\"checkbox\" id=\"emergencyWarning\" />Emergency warning?</label>"
					+"<input type=\"hidden\" id=\"address\" value=\"\">"
					+"</form>";
					const selectBox=document.getElementById("selectBox");
					selectBox.addEventListener("click",function(){showCheckboxes()});
					
					const fileVideo=document.getElementById("fileVideo");
				    const saveButton=document.getElementById("saveButton");
				    
				    
				    saveButton.addEventListener("click",function(){
				    	
				    	var title=document.getElementById("title").value;
				    	var categories="";
				    	for(var j=(i-1);j>0;j--){
							var idElement=""+j;
							var element= document.getElementById(j);
							if(element.checked){
								categories+=element.value+", ";
							}
						}
				    	if(!checkTitle(title)){
					    	alert("You must enter title!");
					    }else if(!checkCategories(categories)){
					    	alert("You must choose categories!");
					    }else if(checkVideo(document.getElementById("fileVideo"))){
					    	numOfPictures=0;
					    	var description=document.getElementById("description").value;
					    	var file = document.getElementById("fileVideo").files[0];
					    	var address = document.getElementById("address").value;
					    	
					    	var formdata = new FormData();
					    	formdata.append("type",type);
					        formdata.append("title", title);
					        formdata.append("description", description);
					        formdata.append("video", file);
					        formdata.append("address", address);
					        
					        formdata.append("categories", categories);
					        
					        var emergencyWarning="";
					        var checkBox= document.getElementById("emergencyWarning");
					        if(checkBox.checked){
					        	emergencyWarning="yes";
							}else{
								emergencyWarning="no";
							}
					        formdata.append("emergencyWarning", emergencyWarning);
					        
					        var xhr = new XMLHttpRequest();    
					        xhr.open("POST","UploadImageController", true);

					        xhr.send(formdata);
					        xhr.onload = function(e) {

					            if (this.status == 200) {

					            	document.getElementById("modalClose").click();
								       showItems();

					            }

					        };  
					    }
				    	
				    	
				    });	
					
		    	var mapCon = document.getElementById("map");
	            google.maps.event.addDomListener(window, 'load', myMap(mapCon));
		    });	
		 
		 var numOfPictures=0;
		 pictures.addEventListener("click",function(){
			 	type = "pictures";
			 	var options="";
				 var b=1;
				 categories.forEach(category => {
					 options+="  <label for=\""+b+"\"><input type=\"checkbox\" id=\""+b+"\" value=\""+category+"\" />"+category+"</label>";
					 b++;
				 });
		    	body.innerHTML="<form id=\"forma\" enctype=\"multipart/form-data\">"
		    		+"<p style=\"height:4%; width:100%; font-size:1vw; color:#2471A3; text-align: left;\">Title: *</p>"
					+"<input type=\"text\" id=\"title\" style=\"height:8%; width:100%; font-size:1vw; color:#2471A3;\">"
					+"<br></br>"
					+"<p style=\"height:4%; width:100%; font-size:1vw; color:#2471A3; text-align: left;\">Description:</p>"
					+"<textarea id=\"description\" style=\"height:16%; width:100%; font-size:1vw; color:#2471A3;\"></textarea>"
					+"<br></br>"
					+"<table style=\"width: 100%; height: 5vh; border: 1px; border-color: #2471A3;\">"
					+"<tr>"
					+"<td style=\"width:30%\">"
					+"<p style=\"height:4%; width:100%; font-size:1vw; color:#2471A3; text-align: left;\">Choose a picture: *</p>"
					+"</td>"
					+"<td style=\"width:70%\">"
					+"<input type=\"file\" style=\"width:100%; height:50%; \" id=\"fileImage\" name=\"file\" class=\"file\" accept=\"image/*\" multiple/>"
					+"</td>"
					+"</tr>"
					+"</table>"
					+"<br></br>"
					+"<p style=\"height:4%; width:100%; font-size:1vw; color:#2471A3; text-align: left;\">Choose categorie: *</p>"
					+"<div class=\"multiselect\">"
				    +"<div class=\"selectBox\" id=\"selectBox\">"
				    +" <select><option>Select an option</option></select>"
				    +" <div class=\"overSelect\"></div></div>"
				    +"<div id=\"checkboxes\">"
				    +options
				    +"</div></div>"
					+"<br></br>"
					+"<div id=\"map\" style=\"width:100%; height:40vh;\"></div>"
					+"<br></br>"
					+"<input type=\"button\" class=\"btn btn-primary\" id=\"saveButton\" style=\"float:right;\" value=\"Save\"></input>"
					+"<input type=\"checkbox\" id=\"emergencyWarning\" />Emergency warning?</label>"
					+"<input type=\"hidden\" id=\"address\" value=\"\">"
					+"</form>";
					const selectBox=document.getElementById("selectBox");
					selectBox.addEventListener("click",function(){showCheckboxes()});
					
					
					var mapCon = document.getElementById("map");
		            google.maps.event.addDomListener(window, 'load', myMap(mapCon));
		            	
		            	
		            	
		    	const fileImage=document.getElementById("fileImage");
			    const tableImages=document.getElementById("tableImages");
			    const saveButton=document.getElementById("saveButton");
			    
			    
			    saveButton.addEventListener("click",function(){
			    	var title=document.getElementById("title").value;
			    	var categories="";
			    	console.log("b "+b);
			    	for(var j=(b-1);j>0;j--){
						var idElement=""+j;
						var element= document.getElementById(j);
						if(element.checked){
							categories+=element.value+", ";
						}
					}
			    	
			    	if(!checkTitle(title)){
				    	alert("You must enter title!");
				    }else if(!checkCategories(categories)){
				    	alert("You must choose categories!");
				    }else if(checkImage(document.getElementById("fileImage"))){
			    		numOfPictures=0;
				    	var description=document.getElementById("description").value;
				    	var files = document.getElementById("fileImage").files;
				    	var address = document.getElementById("address").value;
				    	
				    	var formdata = new FormData();
				    	formdata.append("type",type);
				        formdata.append("title", title);
				        formdata.append("description", description);
				        formdata.append("address", address);
				        formdata.append("length", (""+files.length));
				        
				        formdata.append("categories", categories);
				        var emergencyWarning="";
				        var checkBox= document.getElementById("emergencyWarning");
				        if(checkBox.checked){
				        	emergencyWarning="yes";
						}else{
							emergencyWarning="no";
						}
				        formdata.append("emergencyWarning", emergencyWarning);
				        
				        for(var i=0; i< files.length; i++){
				        	formdata.append(("file"+i), files[i]);
				        }
				        var xhr = new XMLHttpRequest();    
				        xhr.open("POST","UploadImageController", true);

				        xhr.send(formdata);
				        xhr.onload = function(e) {

				            if (this.status == 200) {

				            	document.getElementById("modalClose").click();
							       showItems();

				            }

				        }; 
				        
			    	}
			    	});	
			    
		    });	
		    
		 function myMap(mapCon) {
             var mapProp = {
                 center: new google.maps.LatLng(44.772182,17.191000),
                 zoom:5
             };
             var geocoder = new google.maps.Geocoder();
             var map = new google.maps.Map(mapCon,mapProp);
             google.maps.event.addListener(map, 'click', function(event) {
                 placeMarker(event.latLng);
             });
             
             var marker;
             function placeMarker(location) {
                 if(marker){
                     marker.setPosition(location);
                 }else{
                     marker = new google.maps.Marker({ 
                         position: location, 
                         map: map
                     });
                 }
                 document.getElementById("address").value = location;
             }
         }

         
		 
		 function checkYouTubeURL(url){
			    var tmp = /^(?:https?:\/\/)?(?:www\.)?(?:youtu\.be\/|youtube\.com\/(?:embed\/|v\/|watch\?v=|watch\?.+&v=))((\w|-){11})(?:\S+)?$/;
			    if(url.match(tmp)){
			    	return true;
			    } else {
			    	alert("Youtube URL is not valid!");
			    	return false;
			    }
		}
		
		 function checkLink(url){
			 var pattern = new RegExp('^(https?:\\/\\/)?' + '((([a-z\\d]([a-z\\d-]*[a-z\\d])*)\\.)+[a-z]{2,}|' + '((\\d{1,3}\\.){3}\\d{1,3}))' + '(\\:\\d+)?' + '(\\/[-a-z\\d%@_.~+&:]*)*' + '(\\?[;&a-z\\d%@_.,~+&:=-]*)?' + '(\\#[-a-z\\d_]*)?$', 'i'); 
		     var result = pattern.test(url);
		     if(!result){
		    	 alert("URL is not valid!");
		    	 return result;
		     }else{
		    	 return result;
		     }
		     
		 }
		 
		 function checkImage(imageInput){
			 
				if (imageInput.files[0] == null) {
				      alert ("You must choose image file!");
				      return false;
				    }
				return true;
		 }
		 
		 function checkVideo(video){
			 
				if(video.files[0] != null){
					
					return true;
				}
				alert('You must choose video file!');
				return false;
			 }
		 
		 function checkTitle(title){
			 if(title===""){
				 return false;
				 }
			 return true;
		 }
		 function checkCategories(categories){
			 if(categories===""){
				 return false;
				 }
			 return true;
		 }
		    
		 
		 setInterval(function(){
			
			}, 30000)
			
		
</script></body>
</html>