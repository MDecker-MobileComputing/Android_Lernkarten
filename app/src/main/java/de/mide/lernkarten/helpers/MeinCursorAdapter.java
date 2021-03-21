package de.mide.lernkarten.helpers;

import de.mide.lernkarten.R;

import static de.mide.lernkarten.helpers.IGlobaleKonstanten.TAG4LOGGING;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Adapter-Objekt zur Verbindung zwischen Cursor-Objekt mit allen Lernkarten von Datenbank
 * und Liste zur Darstellung.
 *
 * Siehe
 * <a href="https://coderwall.com/p/fmavhg/android-cursoradapter-with-custom-layout-and-how-to-use-it">hier</a>
 * für ein Beispiel zur Verwendung der Klasse {@link CursorAdapter}.
 * <br><br>
 *
 * This file is licensed under the terms of the BSD 3-Clause License.
 */
public class MeinCursorAdapter extends CursorAdapter {

    /** Wird benötigt, um Layout für einzelne Zeile "aufzublasen". */
    private LayoutInflater _inflater = null;

    /** Index von Spalte mit Text für Vorderseite. */
    private int _columnIndexVorne = -1;

    /** Index von Spalte mit Text für Rückseite. */
    private int _columnIndexHinten = -1;

    /**
     * Konstruktor, übergibt Argumente an Super-Konstruktor und holt {@link LayoutInflater}.
     *
     * @param context  Referenz auf Activity.
     *
     * @param cursor  Cursor, der darzustellende Datensätze repräsentiert.
     *
     * @param flags  Attribute, um Verhalten von Adapter zu steuern.
     */
    public MeinCursorAdapter(Context context, Cursor cursor, int flags) {

        super(context, cursor, flags);

        _inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);

        _columnIndexVorne  = cursor.getColumnIndex("text_vorne");
        _columnIndexHinten = cursor.getColumnIndex("text_hinten");

        Log.i(TAG4LOGGING, "columnIndexVorne=" + _columnIndexVorne +
                                 ", columnIndexHinten=" + _columnIndexHinten);
    }

    /**
     * Erzeugt View für eine Zeile durch "Aufblasen" des entsprechenden Layouts.
     *
     * @param context  Wird nicht benötigt.
     *
     * @param cursor  DB-Cursor, wird nicht benötigt.
     *
     * @param parent  Wird nicht benötigt.
     *
     * @return  Neues View für einen Listen-Eintrag ("Zeile").
     */
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        return _inflater.inflate(R.layout.listview_eintrag, parent, false); // attachToRoot=false
    }

    /**
     * Trägt Werte aus aktuellem Datensatz von {@code cursor} in {@code view} ein.
     *
     * @param view  View-Element für Listen-"Zeile", in das der aktuelle Wert von {@code cursor}
     *              einzutragen ist.
     *
     * @param context  Wird nicht benötigt.
     *
     * @param cursor  Cursor, aus dem ein Datensatz im UI-Element dargestellt werden soll.
     */
    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        String textVorne  = cursor.getString( _columnIndexVorne  );
        String textHinten = cursor.getString( _columnIndexHinten );

        TextView vorneTextView  = view.findViewById( R.id.vorderseiteListeintragTextView );
        TextView hintenTextView = view.findViewById( R.id.rueckseiteListeintragTextView  );

        vorneTextView.setText(  textVorne );
        hintenTextView.setText( textHinten);
    }

}
