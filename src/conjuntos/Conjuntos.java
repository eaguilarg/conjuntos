
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
        if(!contains(elem))
            if(numElem==size())
                expandCapacity();
            contenido[numElem++]=elem;
            numElem++;
    }
    private void expandCapacity(){
        T[] aux=(T[])new Object[contenido.length*2];
                      
        for(int i=0;i<aux.length;i++){
            aux[i]=contenido[i];
        }
        contenido=aux;
    }
    

    @Override
    public T removeRandom() {
        Random rand=new Random();
        int Nrand=rand.nextInt(numElem-1);
        T elem=contenido[Nrand];
        
        contenido[Nrand]=contenido[numElem-1];
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
    public SetADT union(SetADT set) {
       T [] aux=(T[]) new Object[contenido.length*2];
               
        for(int i=0; i<contenido.length;i++)
            aux[i]=contenido[i];
        contenido=aux;
      Iterator<T> copia=set.iterator();
        
           
    }

       
    @Override
    public boolean contains(T elem) {
        boolean resp=false;
        int i=0;
        while(i<numElem && !contenido[i].equals(elem))
            i++;
        if(contenido[i].equals(elem))
            resp=true;
        return resp;        
    }

    @Override
    public boolean equals(SetADT set) {
        boolean resp=false;
        int i=0;
        if(size()== set.size())
            while(set.contains(contenido[i]) && i<size())
                i++;       
        resp=true;
        return resp;
                
    }

    @Override
    public boolean isEmpty() {
        boolean resp=false;
        if(numElem==0)
            resp=true;
        return resp;
    }

    @Override
    public int size() {
        return numElem;
    }

   
    @Override
    public Iterator<T> iterator() {
        return (Iterator<T>) new iteratorArray(contenido,numElem);
    }

    @Override
    public SetADT intersect(SetADT set) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

 

       
}
