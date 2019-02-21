package com.sdzee.dao;

import java.util.List;

import com.sdzee.beans.Client;

public interface ClientDao {

    void creerClient( Client client );

    void supprimerClient( Client client );

    List<Client> listerClient();

    Client trouverClientParID( Long id );

}
