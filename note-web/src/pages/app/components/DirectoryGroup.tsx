import {FolderIcon} from '@assets/icons/index.ts'
import {FC, KeyboardEventHandler, forwardRef, useEffect, useRef} from 'react'
import '../appPage.css'
import Skeleton from 'react-loading-skeleton'

type Props = {
  directories: Directory[]
  isLoading: boolean
  onPress: (directory: Directory) => void
  onAddNewDirectory: (newDir: Directory) => void
  skeletonCount?: number
  showModal: (title: string, body: string) => void
}

const DirectoryGroup: FC<Props> = (props) => {
  const {isLoading, directories, skeletonCount = 5} = props

  return (
    <>
      {directories.map((directory) => {
        return (
          <Directory
            {...directory}
            editable={false}
            onPress={() => console.log('helo wors')}
            key={directory.directoryId}
            showModal={props.showModal}
          />
        )
      })}
      {isLoading &&
        new Array(skeletonCount).fill(null).map((_item, index) => {
          return <Directory showSkeleton={true} editable={false} key={index} />
        })}
    </>
  )
}

export default DirectoryGroup

type DirectoryProps = Partial<Directory> & {
  onPress?: (directory: Directory) => void // action to do when press this component
  showSkeleton?: boolean // for holding showSkeleton mode or not
  editable?: boolean // state for currently component is editable or not
  isSynchronizing?: boolean // state for holding currently updating or adding to api
  onEndEditing?: (value: string) => void // action to do when press enter in input box
  errorMessage?: string // error message for input box
  showModal?: (title: string, body: string) => void // for showing modal with dynmic contents when necessary
  onCancelNewDir?: () => void
}

export const Directory = forwardRef<HTMLInputElement, DirectoryProps>(
  (props, _ref) => {
    const {
      name,
      directoryId,
      editable,
      isSynchronizing = false,
      showSkeleton,
      onEndEditing,
      errorMessage,
      showModal,
      onCancelNewDir,
    } = props
    const inputRef = useRef<HTMLInputElement | null>(null)

    const onInput: KeyboardEventHandler<HTMLInputElement> = (e) => {
      if (!inputRef.current) return
      inputRef.current.value = e.currentTarget.value
    }

    const onKeyDown: KeyboardEventHandler<HTMLInputElement> = (e) => {
      const inputVal = inputRef.current?.value!
      if (e.key == 'Escape') {
        if (inputVal.trim().length > 1) {
          showModal &&
            showModal(
              'Warning!',
              `Are you sure to cancel for creating folder with "${inputVal}" name?`
            )
        } else {
          onCancelNewDir && onCancelNewDir()
        }
        return
      }
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
          <div
            style={{
              position: 'relative',
            }}
          >
            <input
              ref={inputRef}
              type="text"
              onInput={onInput}
              onKeyDown={onKeyDown}
              style={{
                width: '80px',
                maxWidth: '80px',
                fontSize: '14px',
                outline: errorMessage ? '1px solid var(--error)' : 'none',
              }}
              placeholder="Name"
              disabled={isSynchronizing}
              aria-errormessage={errorMessage}
            />
            <span
              style={{
                color: 'var(--error)',
                fontSize: '10px',
                marginTop: '2px',
                lineHeight: '1 !important',
                position: 'absolute',
                left: '5%',
                bottom: '-24px',
              }}
            >
              {errorMessage}
            </span>
          </div>
        )}
        {!editable && showSkeleton && <Skeleton width={'80px'} />}
      </div>
    )
  }
)
