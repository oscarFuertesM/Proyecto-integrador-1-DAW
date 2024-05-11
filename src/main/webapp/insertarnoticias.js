function editarnoticia(idNoti){
	if(idNoti){
		console.log("estoy aqui");
	fetch('EditarNoticias?id='+idNoti)
	.then(response => response.json())
	.then(data => devolverDatos(data))
	}else{
		console.log("hola");
	}
}

function getParameterByName(name) {
		    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
		    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
		    results = regex.exec(location.search);
		    return results === null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
	}
	
function devolverDatos(datos) {
   document.getElementById("id").value = datos.idNoti;
   document.getElementById("titulo").value = datos.titulo;
   document.getElementById("contenido").value = datos.texto;
   

}
	
window.addEventListener("DOMContentLoaded", function(){
		
		let id = getParameterByName("id");
		editarnoticia(id);
		
	})
	
	/*var guardar = document.getElementById("guardar");
	
	guardar.addEventListener("click", function(){
		
		let id = getParameterByName("id");
		editarnoticia(id);
		
	})*/
