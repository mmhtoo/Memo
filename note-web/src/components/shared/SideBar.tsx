import AddIcon from '@assets/icons/AddIcon.tsx'
import {FC, ReactElement, useEffect, useState} from 'react'
import './sideBar.css'
import HomeIcon from '@assets/icons/HomeIcon.tsx'
import SharedIcon from '@assets/icons/SharedIcon.tsx'
import BookmarkIcon from '@assets/icons/BookmarkIcon.tsx'
import ArchiveIcon from '@assets/icons/ArchiveIcon.tsx'
import ShortcutIcon from '@assets/icons/ShortcutIcon.tsx'
import {useAppSelector} from '@hooks/useRedux.ts'
import {selectRootDir} from '@slices/directorySlice.ts'

type NewNoteButtonProps = {
  onPress?: () => void
}

const NewNoteButton: FC<NewNoteButtonProps> = ({onPress}) => {
  return (
    <div
      onClick={() => {
        onPress && onPress()
      }}
      className="btn p-2 gap-1 mx-3 mt-4 d-flex justify-content-center align-items-end cursor-pointer"
      style={{
        width: '96px',
        borderRadius: '10px',
        backgroundColor: 'var(--green)',
        boxShadow: '0 0 1px rgba(0,0,0,0.15)',
      }}
    >
      <AddIcon width={21} height={21} fill={'#fff'} />
      <span
        className="my-auto text-light fw-bold mt-1"
        style={{
          fontSize: '14px',
        }}
      >
        Note
      </span>
    </div>
  )
}

type SideBarItemProps = {
  onPress?: () => void
  isActive: boolean
  label: string
  icon: ReactElement
}

const SideBarItem: FC<SideBarItemProps> = ({isActive, label, icon}) => {
  return (
    <div
      id="sidebar-item"
      className={`w-100 d-flex justify-content-start py-1 px-3 gap-2 d-flex align-items-center cursor-pointer ${
        isActive ? 'active' : ''
      }`}
      style={{
        borderRadius: '14px',
        background: 'transparent',
      }}
    >
      {icon}
      <span
        className="mt-2 mb-1 fw-bold"
        style={{color: 'var(--carbon)', fontSize: '14px'}}
      >
        {label}
      </span>
    </div>
  )
}

type SideBarType = {
  id: number
  label: string
  icon: ReactElement
}

const SIDE_ITEMS: SideBarType[] = [
  {
    id: 1,
    label: 'Home',
    icon: <HomeIcon width={21} height={21} />,
  },
  {
    id: 2,
    label: 'Bookmarks',
    icon: <BookmarkIcon width={21} height={21} />,
  },
  {
    id: 3,
    label: 'Archives',
    icon: <ArchiveIcon width={21} height={21} />,
  },
  {
    id: 4,
    label: 'Shared',
    icon: <SharedIcon width={21} height={21} />,
  },
  {
    id: 5,
    label: 'Shortcuts',
    icon: <ShortcutIcon width={23} height={23} />,
  },
]

const SideBar: FC = () => {
  const [sideItems, setSideItems] = useState(SIDE_ITEMS)
  const [activeIndex, _setActiveIndex] = useState(0)
  const rootDir = useAppSelector(selectRootDir)

  useEffect(() => {
    if (!rootDir?.id || !rootDir.name) return
    setSideItems((prevItems) => {
      const temp = [...prevItems]
      temp[0] = {...prevItems[0], label: `${rootDir.name || 'Home'} `}
      return temp
    })
  }, [rootDir])

  return (
    <div
      className="px-2 bg-sky-blue h-100 position-fixed"
      style={{
        width: '15%',
        borderRightWidth: '0.5px',
        borderRightStyle: 'solid',
        borderRightColor: 'ButtonShadow',
      }}
    >
      <NewNoteButton />
      <div className="mt-4">
        {sideItems.map((item, index) => (
          <SideBarItem
            label={item.label}
            key={item.id.toString()}
            isActive={activeIndex == index}
            icon={item.icon}
            onPress={() => console.log('pressed')}
          />
        ))}
      </div>
    </div>
  )
}

export default SideBar
