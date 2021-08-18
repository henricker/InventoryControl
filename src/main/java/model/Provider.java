package model;

import java.util.Objects;

public class Provider {
    private Integer id;
    private String name;
    private String cnpj;

    public Provider(Integer id, String name, String cnpj) {
        this.id = id;
        this.name = name;
        this.cnpj = cnpj;
    }

    public Provider(String name, String cnpj) {
        this.name = name;
        this.cnpj = cnpj;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Provider)) return false;
        Provider provider = (Provider) o;
        return Objects.equals(getId(), provider.getId()) && Objects.equals(getName(), provider.getName()) && Objects.equals(getCnpj(), provider.getCnpj());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getCnpj());
    }

    @Override
    public String toString() {
        return this.name;
    }
}
