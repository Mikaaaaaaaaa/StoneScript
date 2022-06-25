package script.basic.service;

import script.basic.help.GeneralHelp;

import java.util.HashMap;
import java.util.Map;

public class StorageService implements GeneralHelp
{
    private final Map<String, Object> VARIABLE;

    public StorageService()
    {
        this.VARIABLE = new HashMap<>();
    }

    /**
     * Adds a value to the {@link #VARIABLE}.
     * Also makes sure, that the type is injected as well.
     *
     * @param key of the variable, to identify it.
     * @param value of the variable, which it holds.
     * @param <T> the type of the variable stored in {@code key + ".type"}
     * @throws Exception when something went wrong, while adding the value to the list.
     */
    public <T> void addValue(String key, T value) throws Exception
    {
        if(key.toLowerCase().endsWith(".type"))
        {
            throw new IllegalArgumentException();
        }
        VARIABLE.put(key, value);
        VARIABLE.put(key + ".type", value.getClass().getName());
    }

    /**
     * Removes a variable out of the system.
     * This includes removing of the type as well.
     *
     * @param key the key, to remove all its parts.
     */
    public void remove(String key)
    {
        VARIABLE.remove(key);
        VARIABLE.remove(key + ".type");
    }

    /**
     * Returns the type of the variable given in the {@link Map} {@link #VARIABLE}.
     * The type is always saved in a seperated key which does have the same key but with .type at the end therefore it is not possible to add key, given with .type at the end.
     *
     * @param key of the variable to get the type from.
     * @return the type of the variable with the key.
     * @throws ClassNotFoundException when the class of the type wasn't found.
     * @throws NullPointerException when the key is empty.
     */
    public Class<?> getType(String key) throws ClassNotFoundException, NullPointerException
    {
        return Class.forName(String.valueOf(VARIABLE.get(key + ".type")));
    }

    /**
     * Returns the value of a variable added with {@link #addValue(String, Object)}.
     * This method does remove broken values.
     * <p>
     * The {@link #VARIABLE} map contains the variable and the type, those are both checked and terminated, if there is any issue.
     * Since the {@link #VARIABLE} contains object, it should be safe to cast the values.
     *
     * @param key of the variable.
     * @param checker this is the type the value should be turned to.
     * @return value of the variable and null if it isn't existing.
     * @param <T> the type of the value cast.
     * @see #remove(String)
     * @see #getType(String)
     */
    @SuppressWarnings("unchecked")
    public <T> T getValue(String key, Class<T> checker)
    {
        if(!VARIABLE.containsKey(key))
        {
            return null;
        }

        try {

            if(!getType(key).getName().equals(checker.getName()))
            {
                throw new ClassCastException();
            }

            return (T) VARIABLE.get(key);
        }
        catch (ClassCastException | ClassNotFoundException classNotFoundException) {
            System.err.println("A broken value was found, terminate it!!!");
            remove(key);
        }
        return null;
    }
}
