import {createSlice} from '@reduxjs/toolkit'
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
  reducers: {},
})

export default userSlice.reducer
export const {} = userSlice.actions
export const selectedUsername = (state: AppState) => state.userReducer.username
