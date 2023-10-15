package org.example;
import java.util.ArrayList;
import java.util.List;

public class Almacen {
    private ArrayList<Articulo> articulos = new ArrayList<>();

    public void addArticulo(Articulo articulo) {
        articulos.add(articulo);
    }

    public boolean existeArticuloConCodigo(String codigo) {
        return findArticuloByCodigo(codigo) != null;
    }

    public void addArticulos(Articulo ... articulos) {
        for(Articulo a : articulos) {
            addArticulo(a);
        }
    }

    /**
     *
     * @param codigoArticulo
     * @return true Si ha borrado articulo, false en caso contrario
     */
    public boolean bajaArticulo(String codigoArticulo) {
        Articulo articulo = findArticuloByCodigo(codigoArticulo);
        articulos.remove(articulo);
        //borrado(codigoArticulo);
        return articulo != null;
    }

    private void borrado(String codigoArticulo) {
        ArrayList<Articulo> copy = new ArrayList<>();
        for(Articulo a : articulos) {
            if(!a.getCodigo().equals(codigoArticulo)) {
                copy.add(a);
            }
        }
        articulos = copy;
    }

    public String listado() {
        String resultado = "";
        for(Articulo a : articulos) {
            resultado += a.toString() + "\r\n";
        }

        return resultado;
    }

    public Articulo findArticuloByCodigo(String codigo) {
        Articulo resultadoBusqueda = null;
        for(Articulo a : articulos) {
            if(a.getCodigo().equals(codigo)) {
                resultadoBusqueda = a;
            }
        }
        return resultadoBusqueda;
    }

    public List<Articulo> getArticulos() {
        return articulos;
    }
}
