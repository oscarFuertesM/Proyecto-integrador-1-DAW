/*function datos(){	 
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

function datosMov(){	 
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

       xhr.open("GET", "GestionMovimientos", false);
       xhr.setRequestHeader("Content-Type", "application/json");
       xhr.send();
       return resultado;
}

window.addEventListener("DOMContentLoaded", datosMov())

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
window.addEventListener("DOMContentLoaded", generarEquipo());*/


const debilidadesPokemon = {
  "Normal": ["Lucha"],
  "Fuego": ["Agua", "Tierra", "Roca"],
  "Agua": ["Planta", "Eléctrico"],
  "Planta": ["Fuego", "Hielo", "Veneno", "Volador", "Bicho"],
  "Eléctrico": ["Tierra"],
  "Hielo": ["Fuego", "Lucha", "Roca"],
  "Lucha": ["Volador", "Psíquico"],
  "Tierra": ["Agua", "Planta", "Hielo"],
  "Volador": ["Eléctrico", "Hielo", "Roca"],
  "Veneno": ["Tierra", "Psíquico"],
  "Psíquico": ["Bicho", "Fantasma", "Siniestro"],
  "Bicho": ["Fuego", "Volador", "Roca"],
  "Roca": ["Agua", "Planta", "Lucha", "Tierra"],
  "Fantasma": ["Fantasma", "Siniestro"],
  "Dragón": ["Hielo", "Dragón"],
  "Siniestro": ["Lucha", "Bicho"],
};

let pokemon;
let movimientos;
let contadores = {};

function obtenerPokemon() {
  let xhr = new XMLHttpRequest();
  xhr.onload = function () {
    if (xhr.readyState === 4 && xhr.status === 200) {
      pokemon = JSON.parse(xhr.responseText);
      generarEquipo();
    }
  };
  xhr.open("GET", "listar", true);
  xhr.setRequestHeader("Content-Type", "application/json");
  xhr.send();
}

function obtenerMovimientos() {
  let xhr = new XMLHttpRequest();
  xhr.onload = function () {
    if (xhr.readyState === 4 && xhr.status === 200) {
      movimientos = JSON.parse(xhr.responseText);
      obtenerPokemon();
    }
  };
  xhr.open("GET", "GestionMovimientos", true);
  xhr.setRequestHeader("Content-Type", "application/json");
  xhr.send();
}

function generarEquipo() {
  var section = document.getElementById('equipo');
  if (section && pokemon) {
    for (var i = 0; i < 6; i++) {
      agregarPokemon(section);
    }
  } else {
    console.error("Error al obtener los datos de los Pokémon.");
  }
}

function agregarPokemon(section) {
  var div = document.createElement('div');
  div.className = 'poke';

  var pokemonSelect = document.createElement('select');
  pokemonSelect.className = 'pokemon';
  pokemonSelect.name = 'Pokemon';
  
  
  for (var j = 0; j < pokemon.length; j++) {
    var option = document.createElement('option');
    option.value = pokemon[j].nombre;
    option.textContent = pokemon[j].nombre;
    pokemonSelect.appendChild(option);
  }

  var movimientosDiv = document.createElement('div');
  movimientosDiv.className = 'movsdiv';

  pokemonSelect.addEventListener('change', function () {
    var tipoPokemon = obtenerTipoPokemon(this.value);
    actualizarMovimientos(movimientosDiv, tipoPokemon);
    actualizarContadores(tipoPokemon, true);
  });

  div.appendChild(pokemonSelect);
  div.appendChild(movimientosDiv);
  section.appendChild(div);
}

function actualizarMovimientos(movimientosDiv, tiposPokemon) {
  movimientosDiv.innerHTML = '';

  var movimientosFiltrados = [];
  for (var k = 0; k < movimientos.length; k++) {
    for (var tipoIndex = 0; tipoIndex < tiposPokemon.length; tipoIndex++) {
      if (movimientos[k].tipo === tiposPokemon[tipoIndex]) {
        movimientosFiltrados.push(movimientos[k]);
        break;
      }
    }
  }

  for (var i = 0; i < 4; i++) {
    var movSelect = document.createElement('select');
    movSelect.className = 'movs';
    movSelect.name = 'movs';

    for (var j = 0; j < movimientosFiltrados.length; j++) {
      var option = document.createElement('option');
      option.value = movimientosFiltrados[j].nombre;
      option.textContent = movimientosFiltrados[j].nombre;
      movSelect.appendChild(option);
    }

    movSelect.addEventListener('change', function () {
      var tipoMovimiento = obtenerTipoMovimiento(this.value);
      actualizarContadores(tipoMovimiento, false);
    });

    movimientosDiv.appendChild(movSelect);
  }
}

function obtenerTipoPokemon(nombrePokemon) {
  var tipos = [];
  for (var l = 0; l < pokemon.length; l++) {
    if (pokemon[l].nombre === nombrePokemon) {
      tipos.push(pokemon[l].tipo1);
      if (pokemon[l].tipo2 && pokemon[l].tipo2 !== '') {
        tipos.push(pokemon[l].tipo2);
      }
      return tipos;
    }
  }
}

function obtenerTipoMovimiento(nombreMovimiento) {
  for (var k = 0; k < movimientos.length; k++) {
    if (movimientos[k].nombre === nombreMovimiento) {
      return movimientos[k].tipo;
    }
  }
}

function actualizarContadores() {
  contadores = {}; // Reiniciamos los contadores

  // Recorremos todos los selectores de Pokémon
  document.querySelectorAll('.pokemon').forEach(function (pokemonSelect) {
    var tipoPokemon = obtenerTipoPokemon(pokemonSelect.value);

    // Restamos las debilidades de este Pokémon
    tipoPokemon.forEach(function (tipo) {
      debilidadesPokemon[tipo].forEach(function (debilidad) {
        contadores[debilidad] = (contadores[debilidad] || 0) - 1;
      });
    });
  });

  // Recorremos todos los selectores de movimientos
  document.querySelectorAll('.movs').forEach(function (movSelect) {
    var tipoMovimiento = obtenerTipoMovimiento(movSelect.value);

    // Si el movimiento tiene un tipo, sumamos las fortalezas
    if (tipoMovimiento) {
      for (var tipo in debilidadesPokemon) {
        if (debilidadesPokemon.hasOwnProperty(tipo)) {
          if (debilidadesPokemon[tipo].includes(tipoMovimiento)) {
            contadores[tipo] = (contadores[tipo] || 0) + 1;
          }
        }
      }
    }
  });

  // Actualizamos los contadores en la interfaz
  for (var tipo in contadores) {
    if (contadores.hasOwnProperty(tipo)) {
      var contador = document.querySelector(`.tiposimg[src="image/tipos/${tipo.toLowerCase()}.png"] + .contador`);
      if (contador) {
        contador.textContent = contadores[tipo];
        
        // Determinamos el color del contador
        var colorClass = '';
        if (contadores[tipo] < 0) {
          colorClass = 'negative';
        } else if (contadores[tipo] > 0) {
          colorClass = 'positive';
        } else {
          colorClass = 'zero';
        }
        
        // Aplicamos la clase de color al contador
        contador.className = 'contador ' + colorClass;
      }
    }
  }
}





window.addEventListener("DOMContentLoaded", obtenerMovimientos);
