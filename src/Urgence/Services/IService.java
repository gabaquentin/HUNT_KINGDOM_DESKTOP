package Urgence.Services;

import java.util.List;

public interface IService<U> {
    public void ajouter(U u);
    public void modifier(U u);
    public void traiter(int id);
    public void supprimer(U u);
    public List<U> afficher();
}
