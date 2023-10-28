import {FolderAddIcon, FolderIcon} from '@assets/icons/index.ts'
import {
  FC,
  KeyboardEventHandler,
  MouseEventHandler,
  forwardRef,
  useEffect,
  useRef,
  useState,
} from 'react'
import '../appPage.css'
import Skeleton from 'react-loading-skeleton'
import {FadeIn} from '@components/animations/index.ts'
import useCreateNewDirectory from '../hooks/useCreateNewDirectory'

type Props = {
  directories: Directory[]
  isLoading: boolean
  onPress: (directory: Directory) => void
  onAddNewDirectory: (newDir: Directory) => void
  skeletonCount?: number
}

const DirectoryGroup: FC<Props> = (props) => {
  const {isLoading, directories, skeletonCount = 5} = props
  const [showAddNew, setShowAddNew] = useState(false)
  const {create} = useCreateNewDirectory((newDir) => {
    setShowAddNew(false)
    props.onAddNewDirectory(newDir)
  })

  const onClickNewFolder: MouseEventHandler<HTMLDivElement> = () => {
    setShowAddNew(true)
  }

  return (
    <div
      className="py-3 mt-3 d-flex flex-row gap-3 flex-wrap"
      style={{
        width: '90%',
      }}
    >
      {showAddNew && (
        <FadeIn>
          <Directory editable={true} onEndEditing={create} />
        </FadeIn>
      )}
      {directories.map((directory) => {
        return (
          <Directory
            {...directory}
            showSkeleton={isLoading}
            onPress={() => console.log('helo wors')}
            key={directory.directoryId}
          />
        )
      })}
      {isLoading &&
        new Array(skeletonCount).fill(null).map((_item, index) => {
          return <Directory key={index} />
        })}
      {!props.isLoading && (
        <div
          id="new-folder-btn"
          onClick={onClickNewFolder}
          className="cursor-pointer d-flex flex-column align-items-center"
        >
          <FolderAddIcon width={80} height={80} fill={'#17b978'} />
          <label>New Folder</label>
        </div>
      )}
    </div>
  )
}

export default DirectoryGroup

type DirectoryProps = Partial<Directory> & {
  onPress?: (directory: Directory) => void
  showSkeleton?: boolean
  editable?: boolean
  isNewAdding?: boolean
  onEndEditing?: (value: string) => void
}

const Directory = forwardRef<HTMLInputElement, DirectoryProps>((props) => {
  const {
    name,
    directoryId,
    editable,
    isNewAdding = false,
    showSkeleton,
    onEndEditing,
  } = props
  const inputRef = useRef<HTMLInputElement | null>(null)

  const onInput: KeyboardEventHandler<HTMLInputElement> = (e) => {
    if (!inputRef.current) return
    inputRef.current.value = e.currentTarget.value
  }

  const onKeyDown: KeyboardEventHandler<HTMLInputElement> = (e) => {
    if (e.key !== 'Enter') return
    onEndEditing && onEndEditing(inputRef.current?.value!)
  }

  useEffect(() => {
    setTimeout(() => {
      if (editable && inputRef.current) inputRef.current.focus()
    }, 10)
  }, [])

  return (
    <div
      id="folder-btn"
      className={
        'cursor-pointer d-flex flex-column align-items-center ' + editable
          ? 'gap-0'
          : 'gap-2'
      }
    >
      {(directoryId || editable) && (
        <FolderIcon width={80} height={80} fill={'#17b978'} />
      )}
      {!editable && showSkeleton && (
        <Skeleton style={{width: '80px', height: '70px'}} />
      )}
      {name && (
        <label
          className="d-block text-center"
          style={{
            width: '80px',
          }}
        >
          {name}
        </label>
      )}
      {editable && (
        <input
          ref={inputRef}
          type="text"
          onInput={onInput}
          onKeyDown={onKeyDown}
          style={{
            width: '80px',
            maxWidth: '80px',
            fontSize: '14px',
          }}
          placeholder="Name"
          disabled={isNewAdding}
        />
      )}
      {!editable && showSkeleton && <Skeleton width={'80px'} />}
    </div>
  )
})
