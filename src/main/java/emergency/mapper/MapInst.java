package emergency.mapper;

public class MapInst<T, U> {
    private Class<T> _typeA;
    private Class<U> _typeB;


    public MapInst(Class<T> typeA, Class<U> typeB) {
        _typeA = typeA;
        _typeB = typeB;
    }

    public MapInst() {
    }

    public Class<T> getTypeA()
    {
        return this._typeA;
    }

    public Class<U> getTypeB()
    {
        return this._typeB;
    }

    public void getTypeA(Class<T> a)
    {
        this._typeA = a;
    }

    public void getTypeB(Class<U> b)
    {
        this._typeB = b;
    }

}