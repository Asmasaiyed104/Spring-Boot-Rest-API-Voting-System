function getCapstone(id) {
	if (document.getElementById("capstone" + id).innerHTML == "") {
		//document.getElementById("capstone" + id).innerHTML = "hello Deepinder!";

		fetch('http://localhost:8080/capstones/' + id) // use HomeController to fetch from our service

			.then(capstone => capstone.json()) // JSONify the data returned
			.then(function(capstone) {      // with the JSON data

				// modify textToDisplay below here!
				var textToDisplay = "";             // create and append to a blank var
				// textToDisplay += "ID: " + capstone.id + "<br>";
				textToDisplay += "vote: " + capstone.vote + "<br>";
				textToDisplay += "information: " + capstone.information + "<br>";
			

				// finally, change our relevant div to display the var
				document.getElementById("capstone" + id).innerHTML = textToDisplay;
			});

	} else {
		document.getElementById("capstone" + id).innerHTML = "";
	}
}
