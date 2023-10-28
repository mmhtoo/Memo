import {PayloadAction, createSlice} from '@reduxjs/toolkit'
import {AppState} from '@store/index.ts'

type DirectoryState = {
  currentDir?: string
  currentDirId?: string
  rootDirectory?: {
    name: string
    id: string
  }
}

const initialState: DirectoryState = {
  currentDir: undefined,
  currentDirId: undefined,
  rootDirectory: undefined,
}

const directorySlice = createSlice({
  name: 'directory-slice',
  initialState,
  reducers: {
    setCurrentDir: (state, action: PayloadAction<string>) => {
      state.currentDir = action.payload
    },
    setCurrentDirId: (state, action: PayloadAction<string | undefined>) => {
      state.currentDirId = action.payload
    },
    setRootDir: (state, action: PayloadAction<{name: string; id: string}>) => {
      state.rootDirectory = action.payload
    },
  },
})

export default directorySlice.reducer
export const {setCurrentDir, setCurrentDirId, setRootDir} =
  directorySlice.actions
export const selectCurrentDir = (state: AppState) =>
  state.directoryReducer.currentDir
export const selectCurrentDirId = (state: AppState) =>
  state.directoryReducer.currentDirId
export const selectRootDir = (state: AppState) =>
  state.directoryReducer.rootDirectory
