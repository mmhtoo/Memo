import {defineConfig} from 'vite'
import react from '@vitejs/plugin-react'
import mkcert from 'vite-plugin-mkcert'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],
  resolve: {
    alias: {
      '@assets': './src/assets',
      '@components': './src/components',
      '@pages': './src/pages',
      '@hooks': './src/hooks',
      '@router': './src/router',
      '@services': './src/services',
      '@slices': './src/slices',
      '@store': './src/store',
      '@constants': './src/constants',
      '@utils': './src/utils',
      '@libs': './src/libs',
      '@api': './src/api',
    },
  },
  server: {
    port: 3000,
  },
})
