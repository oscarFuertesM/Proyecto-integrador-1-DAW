function datos(){	 
  let usuario; 
  let xhr = new XMLHttpRequest();
       xhr.onreadystatechange = function () {
           if (xhr.readyState === 4){
               if (xhr.status === 200) {
                 try{
            	 	usuario = JSON.parse(xhr.responseText);            
      
                 }catch (e) {
					// TODO: handle exception
					
				}
               }                    
           }
       };

       xhr.open("GET", "listar", false);
       xhr.setRequestHeader("Content-Type", "application/json");
       xhr.send();
}

window.addEventListener("DOMContentLoaded", datos())

function generateTeam(pokemons) {
	
	pokemon.forEach(pokemon => {
		
	})
	
	<div class="poke">
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
        </div>
}