function noticias() {
	let resultado;
	let xhr = new XMLHttpRequest();
	xhr.onload = function() {
		if (xhr.readyState === 4) {
			if (xhr.status === 200) {
				try {
					resultado = JSON.parse(xhr.responseText);
					console.log(resultado);
				} catch (e) {
					// TODO: handle exception

				}
			}
		}
	};

	xhr.open("GET", "GestiónNoticia", false);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.send();

	return resultado;
}

window.addEventListener("DOMContentLoaded", noticias())

var datosNoticia = noticias();

function generarnoticias(noticia) {

	var section = document.getElementById('notisid');
	if (section) {

		var div = document.createElement('div');
		div.className = 'publicacion';

		// Aquí es donde se copia el codigo HTML que queremos insertar
		div.innerHTML = "<h2>" + noticia.titulo + "</h2><p>" + noticia.texto + "</p>";

		// Metemos el Div dentro del Section
		section.insertBefore(div, section.firstChild);
	} else {
		console.error("FAIL")
	}
}

if(datosNoticia){
	datosNoticia.forEach(function(noticia){
		generarnoticias(noticia);
	})
}else{
	console.error("No se pudo obtener la información de las noticias")
}

window.addEventListener("DOMContentLoaded", generarnoticias());

















