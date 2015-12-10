window.addEventListener("load", function load() {
	
	var body = document.body;
	var html = document.documentElement;
	var sidebar = document.getElementById("sidebar");
	
	var height = Math.max( body.scrollHeight, body.offsetHeight, html.clientHeight, html.scrollHeight, html.offsetHeight );
	sidebar.style.height = (height - 235) + "px";

	window.onresize = function() {
		var height = Math.max( body.scrollHeight, body.offsetHeight, html.clientHeight, html.scrollHeight, html.offsetHeight );
		sidebar.style.height = (height - 235) + "px";

	};
}, false);