import {useEffect} from 'react'

const runOnce = (callback: () => void | (() => void)) => {
  useEffect(() => {
    const cleanup = callback()
    return () => {
      typeof cleanup === 'function' && cleanup()
    }
  }, [])
}

export default runOnce
