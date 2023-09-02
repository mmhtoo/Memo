import {defineConfig} from 'vite'
import react from '@vitejs/plugin-react'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],
  resolve: {
    alias: {
<<<<<<< HEAD
      '@assets': './src/assets',
      '@components': './src/components',
      '@pages': './src/pages',
      '@hooks': './src/hooks',
      '@router': './src/router',
      '@services': './src/services',
      '@slices': './src/slices',
      '@store': './src/store',
      '@constants': './src/constants',
=======
      pages: '/src/pages',
      assets: '/src/assets',
      components: '/src/components',
      router: '/src/router',
      services: '/src/services',
      slices: '/src/slices',
>>>>>>> 7f3a794790435cd1d1907fe0a0c3a66bad8abd8a
    },
  },
})
