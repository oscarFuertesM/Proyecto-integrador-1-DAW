function datos(){	 
  let resultado; 
  let xhr = new XMLHttpRequest();
       xhr.onload = function () {
           if (xhr.readyState === 4){
               if (xhr.status === 200) {
                 try{
            	 	resultado = JSON.parse(xhr.responseText);            
      				//var pokemon = xhr.responseText;
      				console.log(resultado);
                 }catch (e) {
					// TODO: handle exception
					
				}
               }                    
           }
       };

       xhr.open("GET", "listar", false);
       xhr.setRequestHeader("Content-Type", "application/json");
       xhr.send();
       return resultado;
}

window.addEventListener("DOMContentLoaded", datos())

// Insertar los 6 huecos del equipo
function agregarPokemons() {
  
  var section = document.getElementById('equipo');
  if (section){
  
  var div = document.createElement('div');
  div.className = 'poke';

  // Aquí es donde se copia el codigo HTML que queremos insertar
  div.innerHTML = `
    <div class="pokediv">
      <select class="pokemon" name="Pokemon">
        <option value="Charmander">Charmander</option>
      </select>
    </div>
    <div class="movsdiv">
      <select class="movs" name="movs">
        <option value="Lanzallamas">Lanzallamas</option>
      </select>

      <select class="movs" name="movs">
        <option value="Lanzallamas">Lanzallamas</option>
      </select>

      <select class="movs" name="movs">
        <option value="Lanzallamas">Lanzallamas</option>
      </select>

      <select class="movs" name="movs">
        <option value="Lanzallamas">Lanzallamas</option>
      </select>
    </div>
  `;

  // Metemos el Div dentro del Section
  section.appendChild(div);
} else{
  console.error("FAIL")
}
}

// Y con esta función lo repetimos 6 veces, una por cada Pokemon del equipo
function generarEquipo() {
  for (var i = 0; i < 6; i++) {
    agregarPokemons();
  }
}

// Llamar a la función para repetir la generación 6 veces
window.addEventListener("DOMContentLoaded", generarEquipo());


const debilidadesPokemon = {
  "Normal": ["Lucha"],
  "Fuego": ["Agua", "Tierra", "Roca"],
  "Agua": ["Planta", "Eléctrico"],
  "Planta": ["Fuego", "Hielo", "Veneno", "Volador", "Bicho"],
  "Eléctrico": ["Tierra"],
  "Hielo": ["Fuego", "Lucha", "Roca",],
  "Lucha": ["Volador", "Psíquico",],
  "Tierra": ["Agua", "Planta", "Hielo"],
  "Volador": ["Eléctrico", "Hielo", "Roca"],
  "Veneno": ["Tierra", "Psíquico"],
  "Psíquico": ["Bicho", "Fantasma", "Siniestro"],
  "Bicho": ["Fuego", "Volador", "Roca"],
  "Roca": ["Agua", "Planta", "Lucha", "Tierra",],
  "Fantasma": ["Fantasma", "Siniestro"],
  "Dragón": ["Hielo", "Dragón",],
  "Siniestro": ["Lucha", "Bicho",],
  
};