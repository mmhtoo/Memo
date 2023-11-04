import {HEIGHT} from '@constants/sharedDatas.ts'
import {FC} from 'react'
import LogoIcon from './LogoIcon.tsx'
import {useAppSelector} from '@hooks/useRedux.ts'
import {selectUserIdAndName} from '@slices/userSlice.ts'
import {LogoutIcon, UserIcon} from '@assets/icons/index.ts'

const Header: FC = () => {
  const {username} = useAppSelector(selectUserIdAndName)
  return (
    <header
      className="px-5 sticky-top bg-sky-blue d-flex justify-content-between align-items-center"
      style={{
        height: `${HEIGHT * 0.08}px`,
        borderBottomWidth: '0.5px',
        borderBottomStyle: 'solid',
        borderBottomColor: 'ButtonShadow',
      }}
    >
      <LogoIcon />
      <div className="d-flex gap-5">
        <div className="d-flex gap-1 align-items-center cursor-pointer">
          <UserIcon width={21} height={21} />
          <span
            style={{
              color: 'var(--carbon)',
              fontSize: 16,
              fontWeight: 'bold',
              letterSpacing: '1px',
            }}
          >
            {username}
          </span>
        </div>
        <div className="d-flex gap-1 align-items-center cursor-pointer">
          <LogoutIcon width={21} height={21} fill={'#ff5959'} />
          <span
            style={{
              color: 'var(--error)',
              fontSize: 16,
              fontWeight: 'bold',
              letterSpacing: '1px',
            }}
          >
            Logout
          </span>
        </div>
      </div>
    </header>
  )
}

export default Header
