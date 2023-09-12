import {QueryClientProvider} from '@tanstack/react-query'
import AppRouter from '@router/index.tsx'
import queryClient from '@libs/react-query/reactQuery.ts'
import {ToastContainer} from 'react-toastify'
import 'react-toastify/dist/ReactToastify.css'
import {Provider} from 'react-redux'
import store, {persistor} from '@store/index.ts'
import {PersistGate} from 'redux-persist/integration/react'

const App = () => {
  return (
    <Provider store={store}>
      <PersistGate persistor={persistor} loading={null}>
        <QueryClientProvider client={queryClient}>
          <AppRouter />
          <ToastContainer />
        </QueryClientProvider>
      </PersistGate>
    </Provider>
  )
}
export default App
