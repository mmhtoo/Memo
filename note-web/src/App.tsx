import {msg} from 'pages'
import {Button} from 'components/buttons'

const App = () => {
  return (
    <div
      style={{
        width: '300px',
      }}
    >
      <Button onClick={() => console.log('pressed button')} text={'Login'} />
      <Button
        onClick={() => console.log('pressed button')}
        style={{backgroundColor: 'red'}}
        text={'Login'}
      />
    </div>
  )
}

export default App
