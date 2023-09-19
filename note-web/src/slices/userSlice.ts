import {PayloadAction, createSlice} from '@reduxjs/toolkit'
import {AppState} from '@store/index'

type UserState = {
  token?: string
  username?: string
}

const initialState: UserState = {
  token: undefined,
  username: 'Memo',
}

const userSlice = createSlice({
  name: 'user-slice',
  initialState,
  reducers: {
    saveToken: (state, action: PayloadAction<string>) => {
      state.token = action.payload
    },
    removeToken: (state) => {
      state.token = undefined
    },
  },
})

export default userSlice.reducer
export const {saveToken, removeToken} = userSlice.actions
export const selectedUsername = (state: AppState) => state.userReducer.username
export const selectedToken = (state: AppState) => state.userReducer.token
