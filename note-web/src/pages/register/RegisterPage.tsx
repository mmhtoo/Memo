import routes from '@constants/routes.ts'
import defineTitle from '@utils/defineTitle.ts'
import runOnce from '@utils/runOnce.ts'
import {FC, useState} from 'react'
import {Button, Col, Container, Form, Row} from 'react-bootstrap'
import {Link} from 'react-router-dom'
import {FadeIn} from '@components/animations/index.ts'

const RegisterPage: FC = () => {
  const [error, setError] = useState('')

  runOnce(() => {
    defineTitle('Memo | Sign Up')
    setTimeout(() => {
      setError('Error')
    }, 4000)
  })

  return (
    <Container>
      <FadeIn>
        <Row
          style={{
            display: 'flex',
            justifyContent: 'center',
            flexWrap: 'wrap',
            padding: '180px 8px',
          }}
        >
          <Col
            xs={10}
            sm={7}
            md={8}
            lg={6}
            xl={4}
            style={{
              backgroundColor: 'var(--snow)',
              borderRadius: 4,
              boxShadow: '0 0 4px var(--fade-dark)',
              padding: '32px 16px',
            }}
          >
            <Container>
              <h3 className="text-center" style={{color: 'var(--green)'}}>
                Sign Up
              </h3>
              <p
                className="text-center"
                style={{fontSize: '12px', color: 'var(--pale-dark)'}}
              >
                Save you notes with us, sign up your account!
              </p>
            </Container>
            <Form className="px-2 mt-3">
              <Form.Group className="mb-2">
                <Form.Label>Username</Form.Label>
                <Form.Control
                  type={'text'}
                  placeholder={'Enter username'}
                  autoFocus
                />
                <span className="error"></span>
              </Form.Group>
              <Form.Group className="mb-2">
                <Form.Label>Email</Form.Label>
                <Form.Control
                  type={'email'}
                  placeholder={'Enter email address'}
                />
                <span className="error"></span>
              </Form.Group>
              <Form.Group className="mb-2">
                <Form.Label>Password</Form.Label>
                <Form.Control
                  type={'password'}
                  placeholder={'Enter password'}
                />
                <span className="error"></span>
              </Form.Group>
              <Button className="w-100 mt-2">Sign Up</Button>
              <Container>
                <p
                  className="mt-2 text-center"
                  style={{fontSize: '12px', color: 'var(--pale-dark)'}}
                >
                  Already have an account?
                  <Link to={routes.login} className="ms-1 text-decoration-none">
                    Sign In
                  </Link>
                </p>
              </Container>
            </Form>
          </Col>
        </Row>
      </FadeIn>
    </Container>
  )
}

export default RegisterPage
