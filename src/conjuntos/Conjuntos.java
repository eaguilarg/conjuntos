
package conjuntos;

import java.util.Random;
import java.util.Iterator;
public class Conjuntos <T extends Comparable> implements SetADT<T>, Iterable<T>{
//atributos
    private T contenido[] ;
    private final int DEFAULT_CAPACITY=2;
    private int numElem;
//constructor
    public Conjuntos(){
        numElem=0;
        contenido=(T[])new Object[DEFAULT_CAPACITY];                       
    }
        
    
    @Override
    public void add(T elem) {
        if(!contains(elem)){
            if(numElem==contenido.length)
                expandCapacity();
            contenido[numElem]=(T)elem;
            numElem++;
        }
    }
    private void expandCapacity(){
        T[] aux=(T[])new Object[DEFAULT_CAPACITY*2];
                      
        for(int i=0;i<contenido.length;i++){
            aux[i]=contenido[i];
        }
        contenido=aux;
    }
    

    @Override
    public T removeRandom() {
        Random rand=new Random();
        int Nrand=rand.nextInt(numElem);
        T elem=contenido[Nrand];
        
        contenido[Nrand]=contenido[numElem];
        contenido[numElem]=null;
         numElem--;
            return elem;
    }

    @Override
    public T remove(T elem) {
          
        int pos=buscar(elem);
        if(pos==-1)
            throw new Unchecked("Element not found");
        contenido[pos]=contenido[numElem];
        numElem--;
        return elem;         
     
    }
    
    public int buscar(T elem){
        int pos=0;
        while(pos<numElem &&!contenido[pos].equals(elem))
            pos++;
        if(pos==numElem)
            pos=-1;
        return pos;
    }

    @Override
    public SetADT union(SetADT<T> set) {
      SetADT aux=new Conjuntos();
        Iterator<T> copia=set.iterator();        
        for(int i=0; i<numElem;i++)
            aux.add(contenido[i]);
        for(int j=0;j<set.size();j++)
            aux.add(copia.next());
            
        return aux;   
    }

       
    @Override
    public boolean contains(T elem) {
       
        int i=0;
        while(i<numElem){
           if(contenido[i].equals(elem))
              i=numElem+1;
           i++;
        }
        if(i==numElem+1)
            return true;
        return false;    
    }

    @Override
    public boolean equals(SetADT<T> set) {
        boolean resp=false;
        int i=0;
        if(numElem== set.size()){
            while(i<numElem){
                if(!set.contains(contenido[i]))
                    i=numElem+1;
            }
            if(i==numElem)
                resp=true;       
        }
        return resp;
                
    }

    @Override
    public boolean isEmpty() {
        boolean resp=true;
        if(numEle>0)
            resp=false;
        return resp;
    }

    @Override
    public int size() {
        return numElem;
    }

   
    @Override
    public Iterator<T> iterator() {
        return new iteratorArray(contenido,numElem);
    }

    @Override
    public SetADT intersect(SetADT set) {
        SetADT aux=new Conjuntos();
        
        for(int i=0;i<numElem;i++){
            if(set.contains(contenido[i]))
                aux.add(contenido[i]);
        }
        return aux;
                
    }

   

 

       
}
