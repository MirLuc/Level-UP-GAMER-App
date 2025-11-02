package com.example.login001v.data.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.login001v.data.model.Producto;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class ProductoDao_Impl implements ProductoDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Producto> __insertionAdapterOfProducto;

  public ProductoDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfProducto = new EntityInsertionAdapter<Producto>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `productos` (`id`,`nombre`,`precio`,`cantidad`,`direccion`,`conPapas`,`agrandarBebida`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Producto entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getNombre() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getNombre());
        }
        if (entity.getPrecio() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getPrecio());
        }
        if (entity.getCantidad() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getCantidad());
        }
        if (entity.getDireccion() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getDireccion());
        }
        final int _tmp = entity.getConPapas() ? 1 : 0;
        statement.bindLong(6, _tmp);
        final int _tmp_1 = entity.getAgrandarBebida() ? 1 : 0;
        statement.bindLong(7, _tmp_1);
      }
    };
  }

  @Override
  public Object insertarProducto(final Producto producto,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfProducto.insert(producto);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<Producto>> obtenerProductos() {
    final String _sql = "SELECT * FROM productos";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"productos"}, new Callable<List<Producto>>() {
      @Override
      @NonNull
      public List<Producto> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfNombre = CursorUtil.getColumnIndexOrThrow(_cursor, "nombre");
          final int _cursorIndexOfPrecio = CursorUtil.getColumnIndexOrThrow(_cursor, "precio");
          final int _cursorIndexOfCantidad = CursorUtil.getColumnIndexOrThrow(_cursor, "cantidad");
          final int _cursorIndexOfDireccion = CursorUtil.getColumnIndexOrThrow(_cursor, "direccion");
          final int _cursorIndexOfConPapas = CursorUtil.getColumnIndexOrThrow(_cursor, "conPapas");
          final int _cursorIndexOfAgrandarBebida = CursorUtil.getColumnIndexOrThrow(_cursor, "agrandarBebida");
          final List<Producto> _result = new ArrayList<Producto>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Producto _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpNombre;
            if (_cursor.isNull(_cursorIndexOfNombre)) {
              _tmpNombre = null;
            } else {
              _tmpNombre = _cursor.getString(_cursorIndexOfNombre);
            }
            final String _tmpPrecio;
            if (_cursor.isNull(_cursorIndexOfPrecio)) {
              _tmpPrecio = null;
            } else {
              _tmpPrecio = _cursor.getString(_cursorIndexOfPrecio);
            }
            final String _tmpCantidad;
            if (_cursor.isNull(_cursorIndexOfCantidad)) {
              _tmpCantidad = null;
            } else {
              _tmpCantidad = _cursor.getString(_cursorIndexOfCantidad);
            }
            final String _tmpDireccion;
            if (_cursor.isNull(_cursorIndexOfDireccion)) {
              _tmpDireccion = null;
            } else {
              _tmpDireccion = _cursor.getString(_cursorIndexOfDireccion);
            }
            final boolean _tmpConPapas;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfConPapas);
            _tmpConPapas = _tmp != 0;
            final boolean _tmpAgrandarBebida;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfAgrandarBebida);
            _tmpAgrandarBebida = _tmp_1 != 0;
            _item = new Producto(_tmpId,_tmpNombre,_tmpPrecio,_tmpCantidad,_tmpDireccion,_tmpConPapas,_tmpAgrandarBebida);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
