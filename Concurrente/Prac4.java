import java.security.KeyStore.Entry;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class Prac4 {
    public static void main(final String[] args) {
        testGraficas();
    }


    public static void testGraficas() {
        Grafica dosCompleta=new Grafica();
        dosCompleta.addNodo("A");
        LinkedList<String> lista=new LinkedList<>();
        lista.add("B");
        dosCompleta.addNodoAndLinkBothWays("B",lista);
        System.out.println(dosCompleta);
    }







    public static class Grafica {
        HashMap<String,Nodo> nodos;
        Integer numNodos;


        private class Nodo {
            private String nombre;
            private HashMap<String, Nodo> adyacencias;

            public Nodo(String name){
                nombre= name;
                adyacencias=new HashMap<>();
            }

            public Nodo(final String name, final HashMap<String,Nodo> neigbors){
                nombre=name;
                adyacencias = new HashMap<>();
            }

            public String getName() {
                return nombre;
            }

            public void setName(final String name) {
                nombre = name;
            }

            public HashMap<String,Nodo> getAdyacencias() {
                return adyacencias;
            }

            public void SetAdyacencias(final HashMap<String,Nodo> neighbors) {
                adyacencias = neighbors;
            }

            @Override
            public String toString() {
                String s = this.nombre + "\n";
                Iterator i = adyacencias.entrySet().iterator();
                Map.Entry<String,Nodo> entrada;
                while(i.hasNext()){
                    entrada=(Map.Entry<String,Nodo>)i.next();
                    
                    s+="\n "+entrada.getKey();
                }
                return s;
            }

            public void addNeighbor(Nodo n){
                if(adyacencias.get(n.getName())!=null){
                    this.adyacencias.put(n.getName(), n);
                    //n.adyacencias.put(this.nombre, this);
                }
                
            }

            public void removeNeighbor(String n){
                if(adyacencias.get(n)==null)
                    return;
                adyacencias.remove(n);
                
                //n.getAdyacencias().remove(nombre);
            }

        }



        public Grafica(){
            numNodos=0;
            nodos=new HashMap<>();
        }
        public Nodo addNodo(String nombreNodo){
            Nodo nuevoNodo=new Nodo(nombreNodo);
            nodos.put(nombreNodo, nuevoNodo);  
            return nuevoNodo;
        }

        /**
         * Agrega el nodo y agrega la relacion del nodo hacia los vecinos pero no de los vecinos al nodo.
         * Si no existe el nodo, entonces se le asigna null en la lista de vecinos del nodo. 
         * @param nombre
         * @param listaVecinos
         */
        public Nodo addNodoAndLink(String nombre, LinkedList<String> listaVecinos){
            
            Nodo nuevoNodo=addNodo(nombre);
            
            for (String nombreNodo : listaVecinos) {

                nuevoNodo.addNeighbor(nodos.get(nombreNodo));
            }
            return nuevoNodo;
        }
        

        /**
         * Agrega el nodo y agrega relaciones hacia los vecinos.
         * De la misma manera agrega una relacion desde los vencinos hacia el nodo de origen.
         * Si los vecinos no existen en la grafica, entonces se le asigna null a la entrada.
         * @param nombre
         * @param listaVecinos
         */
        public Nodo addNodoAndLinkBothWays(String nombre,LinkedList<String> listaVecinos){
            Nodo nuevoNodo = addNodoAndLink(nombre, listaVecinos);
            for (String vecino : listaVecinos) {
                Nodo n = nodos.get(vecino);
                n.addNeighbor(nuevoNodo);
            }
            return nuevoNodo;
        }

        public void  removeNodo(String nombreNodo){
            if(nodos.get(nombreNodo)==null)
                return;
            
            Set<String> setNodos=nodos.keySet();
            for (String nodo : setNodos) {
                nodos.get(nodo).removeNeighbor(nombreNodo);
            }
        }


        @Override
        public String toString() {
            String s ="\n";
                Iterator i = nodos.entrySet().iterator();
                Map.Entry<String,Nodo> entrada;
                while(i.hasNext()){
                    entrada=(Map.Entry<String,Nodo>)i.next();
                    
                    s+="Nodo: "+entrada.getKey();
                    s+="\n"+entrada.getValue().toString();
                }
                return s;
        }

    }
}