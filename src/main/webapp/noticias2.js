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
		//div.innerHTML = '<h2>' + noticia.titulo + '</h2><p>' + noticia.texto + "</p><button><a href='insertarnoticias.html?id="+noticia.idNoti+"'>Editar</a></button>";
		div.innerHTML = '<h2 class="titulo">' + noticia.titulo + '</h2><p class="texto">' + noticia.texto + "</p><button><a href='insertarnoticias.html?id="+noticia.idNoti+"'>Editar</a></button>";
		
		// Metemos el Div dentro del Section
		section.insertBefore(div, section.firstChild);
	} else {
	}
}

if(datosNoticia){
	datosNoticia.forEach(function(noticia){
		generarnoticias(noticia);
	})
}else{
	console.error("No se pudo obtener la información de las noticias")
}

//window.addEventListener("DOMContentLoaded", generarnoticias());

/*function llamadaEditar(idNoti) {
    var xhr = new XMLHttpRequest();
    xhr.open('GET', 'EditarNoticias?id=' + idNoti);
    xhr.setRequestHeader('Content-Type', 'application/json');
    
    xhr.onload = function() {
        if (xhr.status === 200) {
            var data = JSON.parse(xhr.responseText);
            devolverDatos(data);
        } else {
            console.error('Error al obtener los datos. Código de estado:', xhr.status);
        }
    };
    
    xhr.onerror = function() {
        console.error('Error de red al intentar obtener los datos.');
    };
    
    xhr.send();
}



function pruebafetch(idNoti){
	fetch('EditarNoticias?id='+idNoti)
	.then(response => response.json())
	.then(data => devolverDatos(data))
}


function getParameterByName(name) {
		    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
		    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
		    results = regex.exec(location.search);
		    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
	}
	
/*function devolverDatos(datos){
	document.getElementsByClassName("titulo").value = datos.titulo;
	document.getElementsByClassName("texto").value = datos.texto;
}
function devolverDatos(datos) {
    // Se asume que solo hay un elemento con la clase 'titulo' y 'texto'
    var tituloElement = document.querySelector('.titulo');
    var textoElement = document.querySelector('.texto');

    // Verifica si los elementos existen antes de establecer su valor
    if (tituloElement) {
        tituloElement.value = datos.titulo;
    } else {
        console.error('No se encontró ningún elemento con la clase "titulo"');
    }

    if (textoElement) {
        textoElement.value = datos.texto;
    } else {
        console.error('No se encontró ningún elemento con la clase "texto"');
    }
}
	
	window.addEventListener("DOMContentLoaded", function(){
		
		let id = getParameterByName("id");
		pruebafetch(id);
		
	})*/

