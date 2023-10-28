import {PayloadAction, createSlice} from '@reduxjs/toolkit'
import {AppState} from '@store/index'

type UserState = {
  token?: string
  username?: string
  userId?: string
}

const initialState: UserState = {
  token: undefined,
  username: undefined,
  userId: undefined,
}

const userSlice = createSlice({
  name: 'user-slice',
  initialState,
  reducers: {
    setToken: (state, action: PayloadAction<{token?: string}>) => {
      state.token = action.payload.token
    },
    setUserIdAndName: (
      state,
      action: PayloadAction<{userId?: string; username?: string}>
    ) => {
      state.userId = action.payload.userId
      state.username = action.payload.username
    },
  },
})

export default userSlice.reducer
export const {setToken, setUserIdAndName} = userSlice.actions
export const selectUserIdAndName = (state: AppState) => {
  const {userId, username} = state.userReducer
  return {userId, username}
}
export const selectToken = (state: AppState) => state.userReducer.token
