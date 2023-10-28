import {combineReducers, configureStore} from '@reduxjs/toolkit'
import userReducer from '@slices/userSlice.ts'
import directoryReducer from '@slices/directorySlice.ts'
import storage from 'redux-persist/lib/storage'
import {
  FLUSH,
  PAUSE,
  PERSIST,
  PURGE,
  REGISTER,
  REHYDRATE,
  persistReducer,
  persistStore,
} from 'redux-persist'

const persistConfig = {
  key: 'memo',
  storage,
}

const reducer = combineReducers({
  userReducer,
  directoryReducer,
})

const persistedReducers = persistReducer(persistConfig, reducer)

const store = configureStore({
  reducer: persistedReducers,
  middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware({
      serializableCheck: {
        ignoredActions: [FLUSH, REHYDRATE, PAUSE, PERSIST, PURGE, REGISTER],
      },
    }),
})

export const persistor = persistStore(store)

export default store
export type AppDispatch = () => typeof store.dispatch
export type AppState = ReturnType<typeof persistedReducers>
