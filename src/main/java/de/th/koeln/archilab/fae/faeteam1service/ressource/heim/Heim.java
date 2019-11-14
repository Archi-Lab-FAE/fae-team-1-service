package de.th.koeln.archilab.fae.faeteam1service.ressource.heim;

import de.th.koeln.archilab.fae.faeteam1service.ressource.erkranktePerson.ErkranktePerson;
import de.th.koeln.archilab.fae.faeteam1service.ressource.knopf.Knopf;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Heim
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private String adresse;
    private String telefonnummer; //String, da es auch bestimmte Zeichen enthalten kann (/ als Beispiel).

    @OneToMany(mappedBy = "heim", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ErkranktePerson> erkranktePersonen;

    @OneToMany(mappedBy = "heim", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Knopf> knoepfe;

    public Heim() {}
    public Heim(String name, String adresse, String telefonnummer)
    {
        this.name = name;
        this.adresse = adresse;
        this.telefonnummer = telefonnummer;
    }

    public Integer getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getAdresse()
    {
        return adresse;
    }

    public String getTelefonnummer()
    {
        return telefonnummer;
    }

    public Set<ErkranktePerson> getErkranktePersonen()
    {
        return erkranktePersonen;
    }

    public Set<Knopf> getKnoepfe()
    {
        return knoepfe;
    }

    public void addErkranktePerson(ErkranktePerson erkranktePerson)
    {
        this.erkranktePersonen.add(erkranktePerson);
    }

    public void removeErkranktePerson(ErkranktePerson erkranktePerson)
    {
        this.erkranktePersonen.remove(erkranktePerson);
    }
}
