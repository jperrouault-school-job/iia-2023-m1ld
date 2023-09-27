package fr.formation.patterns.proxy2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import fr.formation.model.Fournisseur;
import fr.formation.model.Produit;

public class ListProduitProxy implements List<Produit> {
    private List<Produit> internalList;
    private Fournisseur parent;

    public ListProduitProxy(Fournisseur parent) {
        this.parent = parent;
    }

    private void load() {
        if (this.internalList == null) {
            System.out.println("SELECT * FROM produit WHERE pro_fournisseur_id = " + parent.getId() + " ...");
            
            this.internalList = new ArrayList<>();

            this.internalList.add(new Produit());
            this.internalList.add(new Produit());
            this.internalList.add(new Produit());
        }
    }

    @Override
    public int size() {
        this.load();
        return this.internalList.size();
    }

    @Override
    public boolean isEmpty() {
        this.load();
        return this.internalList.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        this.load();
        return this.internalList.contains(o);
    }

    @Override
    public Iterator<Produit> iterator() {
        this.load();
        return this.internalList.iterator();
    }

    @Override
    public Object[] toArray() {
        this.load();
        return this.internalList.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        this.load();
        return this.internalList.toArray(a);
    }

    @Override
    public boolean add(Produit e) {
        this.load();
        return this.internalList.add(e);
    }

    @Override
    public boolean remove(Object o) {
        this.load();
        return this.internalList.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        this.load();
        return this.internalList.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends Produit> c) {
        this.load();
        return this.internalList.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends Produit> c) {
        this.load();
        return this.internalList.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        this.load();
        return this.internalList.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        this.load();
        return this.internalList.retainAll(c);
    }

    @Override
    public void clear() {
        this.load();
        this.internalList.clear();
    }

    @Override
    public Produit get(int index) {
        this.load();
        return this.internalList.get(index);
    }

    @Override
    public Produit set(int index, Produit element) {
        throw new CantChangeProxyList();
    }

    @Override
    public void add(int index, Produit element) {
        // On pourrait choisir d'interdire l'ajout d'un élément
        this.load();
        this.internalList.add(index, element);
    }

    @Override
    public Produit remove(int index) {
        this.load();
        return this.internalList.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        this.load();
        return this.internalList.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        this.load();
        return this.internalList.lastIndexOf(o);
    }

    @Override
    public ListIterator<Produit> listIterator() {
        this.load();
        return this.internalList.listIterator();
    }

    @Override
    public ListIterator<Produit> listIterator(int index) {
        this.load();
        return this.internalList.listIterator(index);
    }

    @Override
    public List<Produit> subList(int fromIndex, int toIndex) {
        this.load();
        return this.internalList.subList(fromIndex, toIndex);
    }
}
