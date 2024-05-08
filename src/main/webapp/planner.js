 window.addEventListener("DOMContentLoaded", function(){
	 
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

       xhr.open("GET", "Listar", false);
       xhr.setRequestHeader("Content-Type", "application/json");
       xhr.send();
	

})