<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profile settings</title>
<link rel="stylesheet" type="text/css" href="styles/styleWizard.css">
</head>
<body>



	<div class="div1">
		<form method="POST" action="?action=submitProfile" enctype="multipart/form-data">
			<div class="div2">
				<p>Name:</p>
				<input type="text" name="name" id="name" value="${userBean.user.name}">
				<p>Surname:</p>
				<input type="text" name="surname" id="surname" value="${userBean.user.surname}">
				<p>Username:</p>
				<input type="text" name="username" id="username" value="${userBean.user.username}">
				<p>Password:</p>
				<input type="text" name="password" id="password" value="********"
					disabled="true">
				<p>Mail:</p>
				<input type="text" name="mail" id="mail" value="${userBean.user.mail}">
			</div>
			<div class="div3">
				<p class="imageLabel">Choose a profile picture:</p>
				<img id="image" class="image"/>
				<input type="text" name="imageSrc" id="imageSrc" hidden="true" value="">
				<input type="file" id="file" name="file" class="file"/>
				<br />
				<br />
				<p class="p">Choose a country, region and city:</p>
				<select name="country" id="country"></select>
				<select name="region" id="region"></select> 
				<select name="city" id="city"></select> 
				<br />
				<br />
				<br />
				<table>
					<tr>
            			<td class="checkBoxLabel">Get nottifications?</td>
            			<td>
            				<input type="checkbox" name="notifications" id="notifications" checked="${userBean.user.notificationsInApp}"> </input>
            			</td>
       				</tr>
       				<tr></tr>
       				<tr></tr>
       				<tr></tr>
       				<tr>
            			<td class="checkBoxLabel">Get nottifications as e-mail?</td>
            			<td>
            				<input type="checkbox" name="notificationsOnMail" id="notificationsOnMail" checked="${userBean.user.notificationsOnMail}"> </input>
            			</td>
       				</tr>
				</table>
				<br />
				<br />
				<br />
				<br />
				<input type="submit" name="submit" value="Save">
			</div>
		</form>
	</div>
	<script>
	
	document.getElementById("file").onchange = function () {
	    var reader = new FileReader();

	    reader.onload = function (e) {
	        document.getElementById("image").src = e.target.result;
	    };

	    reader.readAsDataURL(this.files[0]);
	    document.getElementById("imageSrc").value ="";
	};
	
	
	
	const countriesList = document.getElementById("country");
	const regionsList = document.getElementById("region");
	const cityList= document.getElementById("city");
	let countries;
	let regions;
	let city;
	
	countriesList.addEventListener("change", newCountrySelection);

	function newCountrySelection(event) {
		regionsList.innerHTML="";
		cityList.innerHTML="";
		if(document.getElementById("file").files.length === 0){
			const countryData = countries.find(country => country.alpha2Code === event.target.value);
			document.getElementById("image").src =  countryData.flag;
			document.getElementById("imageSrc").value = countryData.flag;
		}
		changeRegions(event.target.value,"cb");
	}
	
	
	regionsList.addEventListener("change", newRegionSelection);

	function newRegionSelection(event) {
		cityList.innerHTML="";
		changeCitys(countriesList[countriesList.selectedIndex].value,event.target.value,"cb");
	}
	
	fetch("https://restcountries.eu/rest/v2/region/europe")
	.then(res => res.json())
	.then(data => initialize(data))
	.catch(err => console.log("Error:", err));

	function initialize(countriesData) {
		  countries = countriesData;
		  let options = "";
		  countries.forEach(country =>  options+="<option value="+country.alpha2Code+">"+country.name+"</option>");
		  countriesList.innerHTML = options;
		  countriesList.selectedIndex = 0;
		  const countryData = countries.find(country => country.alpha2Code === countriesList[countriesList.selectedIndex].value);
			document.getElementById("image").src =  countryData.flag;
		  changeRegions(countriesList[countriesList.selectedIndex].value,"cb");
	}
	
	function changeRegions(countryByAlpha2Code,callback) {
		var e = document.createElement('script');
        e.src =  "http://battuta.medunes.net/api/region/"+countryByAlpha2Code+"/all/?key=87542d7cd45602be618c58b8e941b6d8&callback=cb";
        document.body.appendChild(e);
        let options = "";
        window[callback] = (data) => {
        	initializeRegions(data);
        }
        
	}
	
	function initializeRegions(regionsData){
		  regions = regionsData;
		  let options = "";
		  regions.forEach(region => options+="<option value="+region.region+">"+region.region+"</option>");
		  regionsList.innerHTML = options;
		  regionsList.selectedIndex = 0;
		  if(regionsList.length > 0){
			  changeCitys(countriesList[countriesList.selectedIndex].value,regionsList[regionsList.selectedIndex].value,"cb");
		  }
	}
	
	function changeCitys(countryCode,regionName,callback) {
		var e = document.createElement('script');
        e.src =  "http://battuta.medunes.net/api/city/"+countryCode+"/search/?region="+regionName+"&key=87542d7cd45602be618c58b8e941b6d8&callback=cb";
        document.body.appendChild(e);
        let options = "";
        window[callback] = (data) => {
        	initializeCity(data);
        }
	}
	
	function initializeCity(cityData){
		  city = cityData;
		  let options = "";
		  city.forEach(city => options+="<option value="+city.region+">"+city.city+"</option>");
		  cityList.innerHTML = options;
		  cityList.selectedIndex = 0;
		
	}
	
		
	</script>
</body>
</html>